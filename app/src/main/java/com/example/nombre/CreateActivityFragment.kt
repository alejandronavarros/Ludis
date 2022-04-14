package com.example.nombre

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nombre.databinding.CreateActivityFragmentBinding
import android.widget.Spinner

class CreateActivityFragment : Fragment() {

    companion object {
        fun newInstance() = CreateActivityFragment()
    }

    private lateinit var viewModel: CreateActivityViewModel
    private var _binding : CreateActivityFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CreateActivityFragmentBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.create_activity_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreateActivityViewModel::class.java)
        //binding.spinner.
    }

}