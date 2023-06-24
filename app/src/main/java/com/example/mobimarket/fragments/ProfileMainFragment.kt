package com.example.mobimarket.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobimarket.R
import com.example.mobimarket.databinding.ProfileMainFragmentBinding

class ProfileMainFragment:Fragment() {
    private lateinit var binding: ProfileMainFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfileMainFragmentBinding.inflate(inflater,container,false)
        binding.likedButton.setOnClickListener{
            findNavController().navigate(R.id.action_profileMainFragment_to_changeProfileFragment)
        }
        binding.changeProfileButton.setOnClickListener{
            findNavController().navigate(R.id.action_profileMainFragment_to_changeProfileFragment)
        }
        return binding.root
    }
}