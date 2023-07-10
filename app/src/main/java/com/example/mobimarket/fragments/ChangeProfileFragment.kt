package com.example.mobimarket.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobimarket.R
import com.example.mobimarket.databinding.ChangeProfileFragmentBinding

class ChangeProfileFragment:Fragment() {
    lateinit var binding:ChangeProfileFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ChangeProfileFragmentBinding.inflate(inflater,container,false)
        binding.addPhoneNumberButton.setOnClickListener{
            findNavController().navigate(R.id.action_changeProfileFragment_to_addingNumberFragment)
        }

        binding.cancelEditButton.setOnClickListener{
            findNavController().popBackStack()
        }
        return binding.root
    }
}