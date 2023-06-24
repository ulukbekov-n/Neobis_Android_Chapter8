package com.example.mobimarket.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mobimarket.databinding.MyWalletFragmentBinding

class MyWalletFragment : Fragment() {
    private lateinit var binding: MyWalletFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MyWalletFragmentBinding.inflate(inflater,container,false)
        return binding.root

    }
}