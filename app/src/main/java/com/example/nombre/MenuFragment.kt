package com.example.nombre

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nombre.clases.*
import com.example.nombre.databinding.FragmentMenuBinding
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Global.getUser() == null) {
            Global.setUser(
                UsuarioNormal(
                    "usuario",
                    "nombre",
                    "mail@mail.com",
                    Genero.Hombre,
                    Date(2000, 1, 1)
                )
            )
            //UserActual.getUser().addNotificacion(Notificacion(0,UserActual.getUser()))
        }
        if (Global.getUser().notificaciones.isEmpty()) {
            binding.imageNoti.visibility = View.INVISIBLE
            binding.textNoti.text = ""
        } else {
            if (Global.getUser().notificaciones.size > 9) {
                binding.textNoti.text = "9+"
            } else
                binding.textNoti.text = Global.getUser().notificaciones.size.toString()
        }


        binding.imageActividades.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_deportes)
        }
        binding.imageSocial.setOnClickListener {
            val intent = Intent(context , MapsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}