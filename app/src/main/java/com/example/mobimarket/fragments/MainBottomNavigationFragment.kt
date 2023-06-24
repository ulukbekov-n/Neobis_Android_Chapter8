package com.example.mobimarket.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mobimarket.R
import com.example.mobimarket.databinding.MainBottomNavigationFragmentBinding

class MainBottomNavigationFragment:Fragment() {
    private lateinit var binding:MainBottomNavigationFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = MainBottomNavigationFragmentBinding.inflate(inflater,container,false)
        binding.bottomNav.background = null
//        binding.bottomNav.setOnItemSelectedListener{
//            when(it.itemId){
//                R.id.myHome ->replaceFragment(RegisterFragment())
//                R.id.myWallet ->replaceFragment(MyWalletFragment())
//                R.id.myChat ->replaceFragment(MyChatsFragment())
//                R.id.myProfile ->replaceFragment(ProfileMainFragment())
//
//                else->{
//
//                }
//            }
//            true
//        }
        val bottomNavigationView = binding.bottomNav
        val navHostFragment = childFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)
        return binding.root
    }

//    private fun replaceFragment(fragment: Fragment) {
//        val fragmentManager = childFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.frameLayout, fragment)
//        fragmentTransaction.commit()
//    }
}