package com.example.mobimarket.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobimarket.R
import com.example.mobimarket.databinding.AddingNumberFragmentBinding

class AddingNumberFragment:Fragment() {
    private lateinit var binding:AddingNumberFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddingNumberFragmentBinding.inflate(inflater,container,false)

        binding.numberCancelButton.setOnClickListener{
            findNavController().navigate(R.id.action_addingNumberFragment_to_changeProfileFragment)
        }
        return binding.root
    }
}