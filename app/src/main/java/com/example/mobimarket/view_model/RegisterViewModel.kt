package com.example.mobimarket.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {

    private val _usernameInput = MutableLiveData<String>()
    val usernameInput: LiveData<String> = _usernameInput

    private val _emailInput = MutableLiveData<String>()
    val emailInput: LiveData<String> = _emailInput

    private val _isButtonEnabled = MutableLiveData<Boolean>()
    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled

    private val _isUsernameValid = MutableLiveData<Boolean>()
    val isUsernameValid: LiveData<Boolean> = _isUsernameValid

    private val _isEmailValid = MutableLiveData<Boolean>()
    val isEmailValid: LiveData<Boolean> = _isEmailValid


    fun onUsernameTextChanged(text: CharSequence?) {
        _usernameInput.value = text?.toString()?.trim()
        updateButtonEnabledState()
    }
    fun onEmailTextChanged(text: CharSequence?) {
        _emailInput.value = text?.toString()?.trim()
        updateButtonEnabledState()
    }
    private fun updateButtonEnabledState() {
        val username = _usernameInput.value
        val email = _emailInput.value
        val isEmailValid = !email.isNullOrEmpty() && email.contains("@")
        _isButtonEnabled.value = !username.isNullOrEmpty() && isEmailValid
    }
}