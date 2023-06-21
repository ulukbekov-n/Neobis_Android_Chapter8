package com.example.mobimarket.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mobimarket.databinding.ProfileMainFragmentBinding

class ProfileMainFragment:Fragment() {
    private lateinit var binding:ProfileMainFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfileMainFragmentBinding.inflate(inflater,container,false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}