package com.example.nombre

import DatePickerFragment
import android.app.TimePickerDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.nombre.clases.Deporte
import com.example.nombre.clases.Global
import com.example.nombre.databinding.ActivityCreateEventBinding
import com.google.android.gms.maps.model.LatLng
import java.util.*


class ActivityCreateEvent : AppCompatActivity(){



    private lateinit var binding: ActivityCreateEventBinding
    private lateinit var latLng: LatLng
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityCreateEventBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cargarSpinner()
        checkExtras()
        binding.rEventDate.setOnClickListener { showDatePickerDialog() }
        binding.editTextTime.setOnClickListener{ showTimePickerDialog()}
        binding.imageGoogle.setOnClickListener { clickToMaps() }
        binding.editDescripcion.setOnClickListener { disableTextView() }
        binding.buttonCreate.setOnClickListener { checkFields() }
    }

    private fun clickToMaps(){
        val intent = Intent(this, MapsActivity::class.java)

        if (binding.rEventDate.text.isNotBlank())
            intent.putExtra("fecha",binding.rEventDate.text.toString())
        if (binding.editTextTime.text.isNotBlank())
            intent.putExtra("hora",binding.editTextTime.text.toString())
        if (binding.editJugadores.text.isNotBlank())
            intent.putExtra("jugadores",binding.editJugadores.text.toString())
        if (binding.editPrecio.text.isNotBlank())
            intent.putExtra("precio",binding.editPrecio.text.toString())
        if (binding.editDescripcion.text.isNotBlank())
            intent.putExtra("descripcion",binding.editDescripcion.text.toString())
        intent.putExtra("nivel",binding.nivelPartido.progress)
        intent.putExtra("pos",binding.spinner.selectedItemPosition)
        val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(arg0: Context?, intent: Intent) {
                val action = intent.action
                if (action == "finish_activity") {
                    finish()
                }
            }
        }
        registerReceiver(broadcastReceiver, IntentFilter("finish_activity"))
        startActivity(intent)
    }
    private fun cargarSpinner(){
        when(Global.getDep()) {
            Deporte.Futbol -> {
                val languages = resources.getStringArray(R.array.footballDisplay)

                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_item, languages)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinner.adapter = adapter
            }
            Deporte.Baloncesto -> {
                ArrayAdapter.createFromResource(
                    this,
                    R.array.basketDisplay,
                    android.R.layout.simple_spinner_item
                ).also { adapter ->
                    Log.d("cargarSpinner","Pasa por Baloncesto")
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.spinner.adapter = adapter
                }
            }
            Deporte.Volleybol -> {
                ArrayAdapter.createFromResource(
                    this,
                    R.array.volleyDisplay,
                    android.R.layout.simple_spinner_item
                ).also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.spinner.adapter = adapter
                }
            }
            Deporte.Hockey ->{
                ArrayAdapter.createFromResource(
                    this,
                    R.array.hockeyDisplay,
                    android.R.layout.simple_spinner_item
                ).also { adapter ->

                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.spinner.adapter = adapter
                }
            }
            else ->{
                val arrmile =
                    arrayOf(Global.getDep().toString())
                val adapter = ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_spinner_item,
                    arrmile
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinner.adapter = adapter
            }
        }
    }
    private fun disableTextView(){
        if(binding.textCaracteres.visibility == View.VISIBLE)
            binding.textCaracteres.visibility= View.INVISIBLE
    }
    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }
    private fun showTimePickerDialog(){

        val c = Calendar.getInstance();
        val hour = c.get(Calendar.HOUR_OF_DAY);
        val minute = c.get(Calendar.MINUTE);

        val formato24Horas = true;

        val recogerHora =  TimePickerDialog(this,  TimePickerDialog.OnTimeSetListener {
                timepicker, hour, min ->
            if(hour < 10 && min < 10)
                binding.editTextTime.setText("0$hour:0$min");
            else if (hour < 10)
                binding.editTextTime.setText("0$hour:$min")
            else if (min < 10)
                binding.editTextTime.setText("$hour:0$min")
            else
                binding.editTextTime.setText("$hour:$min")
        }, hour, minute, formato24Horas );


        recogerHora.show();

    }

    private fun checkExtras(){
        val extras = intent.extras
        if (extras != null) {
            if (extras.get("fecha") != null) {
                binding.rEventDate.setText(extras.getString("fecha"))
            }
            if (extras.get("hora") != null)
                binding.editTextTime.setText(extras.getString("hora"))
            if (extras.get("jugadores") != null)
                binding.editJugadores.setText(extras.getString("jugadores"))
            if (extras.get("precio") != null)
                binding.editPrecio.setText(extras.getString("precio"))
            if (extras.get("descripcion") != null)
                binding.editDescripcion.setText(extras.getString("descripcion"))
            if (extras.get("pos") != null)
                binding.spinner.setSelection(extras.getInt("pos"))
            if (extras.get("nivel") != null)
                binding.nivelPartido.progress = extras.getInt("nivel")
            if (extras.get("lat") != null){
                val lat = extras.getDouble("lat")
                val log = extras.getDouble("log")
                latLng = LatLng(lat,log)
                val geocoder = Geocoder(baseContext, Locale.getDefault())
                val address = geocoder.getFromLocation(latLng.latitude,latLng.longitude, 1)
                if(address.size > 0) {
                    binding.editLugar.setText(address.get(0).getAddressLine(0))
                }else {
                    binding.editLugar.setText("${latLng.latitude}, ${latLng.longitude}")
                }
            }
        }
    }
    private fun checkFields(){
        if (binding.editLugar.text.isBlank())
            null


    }
    private fun onDateSelected(day: Int, month: Int, year: Int) {
        /* val string = "$day/$month/$year"
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val date = LocalDate.parse(string, formatter)*/

        binding.rEventDate.setText(""+ day + "/" + (month+1) +"/" + year)
    }
}

