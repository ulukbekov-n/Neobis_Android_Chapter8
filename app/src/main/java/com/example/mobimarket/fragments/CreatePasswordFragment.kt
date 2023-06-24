package com.example.mobimarket.fragments

import android.os.Binder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobimarket.R
import com.example.mobimarket.databinding.CreatePasswordFragmentBinding

class CreatePasswordFragment:Fragment() {
    lateinit var binding:CreatePasswordFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CreatePasswordFragmentBinding.inflate(inflater,container,false)
        binding.passwordBackButton.setOnClickListener{
            findNavController().navigate(R.id.action_createPasswordFragment_to_registerFragment)
        }

        return binding.root
    }
}