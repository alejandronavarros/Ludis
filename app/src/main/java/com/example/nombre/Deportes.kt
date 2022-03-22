package com.example.nombre

import android.app.AlertDialog
import android.content.DialogInterface
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
            setDialog(Deporte.Futbol11)
        }
        binding.buttonBasket.setOnClickListener {
            setDialog(Deporte.Baloncesto)
        }
        binding.buttonPadel.setOnClickListener {
            setDialog(Deporte.Padel)
        }
        binding.buttonRunnning.setOnClickListener {
            setDialog(Deporte.Running)
        }
        binding.buttonSenderismo.setOnClickListener {
            setDialog(Deporte.Trekking)
        }
        binding.buttonTennis.setOnClickListener {
            setDialog(Deporte.Tenis)
        }
        binding.buttonVolley.setOnClickListener {
            setDialog(Deporte.Volleybol)
        }



    }
    fun setDialog(deporte: Deporte){
        val builder: AlertDialog.Builder = activity.let {
            AlertDialog.Builder(it)
        }
        builder.setMessage(R.string.alertChoice)
            .setNegativeButton(R.string.back, null)
            .setPositiveButton(R.string.find,  DialogInterface.OnClickListener {
                    dialog, id-> Global.setDep(deporte)
                findNavController().navigate(R.id.action_deportes_to_searchActivityFragment)
            })
            .setNeutralButton(R.string.create, DialogInterface.OnClickListener {
                dialog, id -> Global.setDep(deporte)
                findNavController().navigate(R.id.action_deportes_to_createActivityFragment)})
        val dialog = builder.create()
        dialog.show()
    }
    fun handleClick() {

    }
}
