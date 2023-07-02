//package com.example.mobimarket.view_model
//
//import CreatePasswordViewModel
//import android.app.Application
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import androidx.navigation.NavController
//
//class CreatePasswordViewModelFactory(
//    private val application: Application,
//    private val navController: NavController
//) : ViewModelProvider.Factory {
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(CreatePasswordViewModel::class.java)) {
//            return CreatePasswordViewModel(application, navController) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
