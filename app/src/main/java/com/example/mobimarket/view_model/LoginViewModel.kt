package com.example.mobimarket.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobimarket.data.LoginRequest
import com.example.mobimarket.data.LoginResponse
import com.example.mobimarket.utils.APIConsumer
import com.example.mobimarket.utils.RetrofitInstance
import com.example.mobimarket.utils.UserHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val authAPI: APIConsumer = RetrofitInstance.apiConsumer

    private val loginEmail = MutableLiveData<String>()
    val emailInput: LiveData<String> = loginEmail

    private val loginPassword = MutableLiveData<String>()
    val passwordInput: LiveData<String> = loginPassword

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    private val _isButtonEnabled = MutableLiveData<Boolean>()
    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled

    private val _loginError = MutableLiveData<Unit>()
    val loginError: LiveData<Unit> get() = _loginError

    fun onEmailTextChanged(text: CharSequence?) {
        loginEmail.value = text?.toString()?.trim()
        updateButtonEnabledState()
    }

    fun onPasswordTextChanged(text: CharSequence?) {
        loginPassword.value = text?.toString()?.trim()
        updateButtonEnabledState()
    }

    private fun updateButtonEnabledState() {
        val email = loginEmail.value
        val password = loginPassword.value

        _isButtonEnabled.value = !email.isNullOrEmpty() && !password.isNullOrEmpty()
    }

    fun login(email: String, password: String) {
        val request = LoginRequest(email, password)
        viewModelScope.launch {
            try {
                val response: Response<LoginResponse> = withContext(Dispatchers.IO) {
                    authAPI.login(request)
                }
                if (response.isSuccessful) {
                    UserHolder.email = email

                    val loginResponse = response.body()
                    val accessToken = loginResponse?.access
                    val refreshToken = loginResponse?.refresh
                    if (refreshToken != null && accessToken != null) {
                        UserHolder.access_token = accessToken.toString()
                    }
                    _loginSuccess.value = true
                } else {
                    _loginSuccess.value = false
                }
            } catch (t: Throwable) {
                _loginError.value = Unit
            }
        }
    }
}