package com.example.nombre

import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.nombre.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var lista: List<MarkerOptions>
    private lateinit var geocoder: Geocoder
    private lateinit var place: LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val extras = intent.extras
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.location_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        geocoder = Geocoder(baseContext, Locale.getDefault())
        binding.botonMapa.setOnClickListener {
            val newintent = Intent(this , ActivityCreateEvent::class.java)
            if(extras != null){
                var aux = extras.getString("fecha")
                if (aux != null)
                    newintent.putExtra("fecha",aux)
                aux = extras.getString("hora")
                if ( aux != null)
                    newintent.putExtra("hora",aux)
                aux = extras.getString("jugadores")
                if (aux != null)
                    newintent.putExtra("jugadores",aux)
                aux = extras.getString("precio")
                if (aux != null)
                    newintent.putExtra("precio",aux)
                aux = extras.getString("descripcion")
                if (aux != null)
                    newintent.putExtra("descripcion",aux)
                val pos = extras.getInt("pos")
                newintent.putExtra("pos",pos)
                val nivel = extras.getInt("nivel")
                newintent.putExtra("nivel",nivel)
            }
            newintent.putExtra("lat", place.latitude)
            newintent.putExtra("log",place.longitude)
            val end = Intent("finish_activity")
            sendBroadcast(end)
            startActivity(newintent)
            finish()
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if(!mMap.uiSettings.isMyLocationButtonEnabled)
            mMap.uiSettings.isMyLocationButtonEnabled = true
        if(!mMap.uiSettings.isCompassEnabled)
            mMap.uiSettings.isCompassEnabled = true
        if(!mMap.uiSettings.isZoomControlsEnabled)
            mMap.uiSettings.isZoomControlsEnabled = true

        //crearMarcas(mMap)
        mMap.setOnMapClickListener { latLng ->
            mMap.clear()
            //crearMarcas(mMap)
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
            place = LatLng(latLng.latitude, latLng.longitude)
            mMap.addMarker(MarkerOptions().position(place))
            val address = geocoder.getFromLocation(latLng.latitude,latLng.longitude, 1)
            binding.botonMapa.visibility = View.VISIBLE
            if(address.size > 0) {
                Log.d("Map", "Address: ${address.get(0).getAddressLine(0)}")
                Log.d("Map", "Ciudad: ${address.get(0).locality}")
                Log.d("Map", "Comunidad: ${address.get(0).adminArea}")
            }else {
                Log.d("Map", "Latitud: ${latLng.latitude}")
                Log.d("Map", "Longitud: ${latLng.longitude}")
            }
        }
    }
    private fun crearMarcas(mMap: GoogleMap){
        for (l in lista) {
            mMap.addMarker(l)
        }
    }
}