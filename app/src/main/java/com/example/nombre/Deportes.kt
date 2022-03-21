package com.example.nombre

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nombre.clases.Deporte
import com.example.nombre.clases.Global
import com.example.nombre.databinding.FragmentDeportesBinding

class Deportes : Fragment() {

    private var _binding: FragmentDeportesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeportesBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonFurbo.setOnClickListener {
            Global.setDep(Deporte.Futbol5)
        }

        }
    fun setDialog(deporte: Deporte){
        val builder: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        }
        builder?.setMessage(R.string.alertChoice)
            ?.setNegativeButton(R.string.back, null)
            ?.setPositiveButton(R.string.find, null)
            ?.setNeutralButton(R.string.create, null)



        val dialog: AlertDialog? = builder?.create()
        dialog?.getButton(AlertDialog.BUTTON_POSITIVE)?.setOnClickListener {
            Global.setDep(deporte)
            findNavController().navigate(R.id)
        }
        dialog.getButton(AlertDialog.BUTTON_NEUTRAL)?.setOnClickListener {
            Global.setDep(deporte)
            findNavController().navigate(R.id.)
        }
        dialog?.show()
    }

}
