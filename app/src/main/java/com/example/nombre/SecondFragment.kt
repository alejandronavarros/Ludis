package com.example.nombre

import DatePickerFragment
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nombre.clases.Genero
import com.example.nombre.clases.Global
import com.example.nombre.clases.UsuarioNormal
import com.example.nombre.databinding.FragmentRegistroBinding

import java.util.*


class Register : Fragment() {

    private var _binding: FragmentRegistroBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegistroBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.butBack.setOnClickListener {
            findNavController().navigate(R.id.action_Reg_to_Log)
        }
        binding.rBirthDate.setOnClickListener { showDatePickerDialog() }
        binding.butReg.setOnClickListener {
            val genero = checkFields()

            if (genero  != 0) {
                val date = binding.rBirthDate.text.toString().split("/").toTypedArray()
                val user = UsuarioNormal(
                    binding.regUsuario.text.toString(),
                    binding.regNombre.text.toString(),
                    binding.editEmailAddress2.text.toString(),
                    getGender(genero),
                    Date(date[2].toInt(),date[1].toInt(),date[0].toInt())
                )
                Global.setUser(user)
                findNavController().navigate(R.id.action_Register_to_menuFragment)
            }


        }
        binding.imagebuttonpass.setOnClickListener {
            hideshowpass(binding.rPassword)
        }
        binding.imagebuttonpass2.setOnClickListener {
            hideshowpass(binding.rPasswordRepeat)
        }
    }

    private fun hideshowpass(pass: EditText) {
        if (pass.transformationMethod == PasswordTransformationMethod.getInstance()) {
            pass.transformationMethod = HideReturnsTransformationMethod.getInstance()
        } else {
            pass.transformationMethod = PasswordTransformationMethod.getInstance()
        }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        activity?.let { datePicker.show(it.supportFragmentManager, "datePicker") }
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        /* val string = "$day/$month/$year"
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val date = LocalDate.parse(string, formatter)*/

        binding.rBirthDate.setText(""+ day + "/" + (month+1) +"/" + year)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkGender(): Int {
        var id = binding.radioGroup.checkedRadioButtonId
        when (id) {
            binding.femaleButton.id -> return 1
            binding.maleButton.id -> return 2
            binding.otherButton.id -> return 3
            else -> return 0
        }
    }

    private fun checkFields(): Int {
        val gender = checkGender()
        val controlador= Controlador()
        if (binding.regUsuario.text.isBlank() || binding.regNombre.text.isBlank() || binding.editEmailAddress2.text.isBlank() || binding.rBirthDate.text.isBlank() || binding.rPassword.text.isBlank() || binding.rPasswordRepeat.text.isBlank() || gender == 0) {
            controlador.setAlert(R.string.registroAlerta)
            return 0
        }
        if (binding.regUsuario.text.length < 8) {
            controlador.setAlert(R.string.usershort)
            return 0
        }
        if (binding.rPassword.text.length < 8) {
            controlador.setAlert(R.string.passshort)
            return 0
        }
        if (!isEmailValid(binding.editEmailAddress2.text)) {
            controlador.setAlert(R.string.emailvalid)
            return 0
        }
        if(binding.rPassword.text.toString() != binding.rPasswordRepeat.text.toString()) {
            controlador.setAlert(R.string.alertPasswords)
            return 0
        }
        return gender
    }

    private fun isEmailValid(email: CharSequence?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }



    private fun getGender(i : Int): Genero{
        when (i) {
            1 -> return Genero.Mujer
            2 -> return Genero.Hombre
            3 -> return Genero.Otro
        }
        return Genero.Mujer
    }
}