package com.example.mobimarket.fragments.signedIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.namespace.R
import com.example.namespace.databinding.MainBottomNavigationFragmentBinding

class MainBottomNavigationFragment:Fragment() {
    private lateinit var binding:MainBottomNavigationFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = MainBottomNavigationFragmentBinding.inflate(inflater,container,false)
        binding.bottomNav.background = null


        val bottomNavigationView = binding.bottomNav
        val navHostFragment = childFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)
        binding.fab.setOnClickListener{
            findNavController().navigate(R.id.action_mainBottomNavigationFragment_to_addProductFragment)
        }
        return binding.root
    }

}