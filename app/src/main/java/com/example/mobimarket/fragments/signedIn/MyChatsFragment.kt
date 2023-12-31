package com.example.mobimarket.fragments.signedIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.namespace.databinding.MyChatsFragmentBinding

class MyChatsFragment:Fragment() {
    lateinit var binding: MyChatsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MyChatsFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }
}