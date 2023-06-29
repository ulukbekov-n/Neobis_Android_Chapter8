package com.example.mobimarket.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobimarket.data.LoginRequest
import com.example.mobimarket.data.LoginResponse
import com.example.mobimarket.data.User
import com.example.mobimarket.utils.APIConsumer
import com.example.mobimarket.utils.RetrofitInstance
import com.example.mobimarket.utils.UserHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val authAPI: APIConsumer = RetrofitInstance.apiConsumer

    private val _loginResponse = MutableLiveData<LoginResponse?>()
    val loginResponse: LiveData<LoginResponse?> get() = _loginResponse

    private val _usernameInput = MutableLiveData<String>()
    val usernameInput: LiveData<String> = _usernameInput

    private val _passwordInput = MutableLiveData<String>()
    val passwordInput: LiveData<String> = _passwordInput

    private val _isButtonEnabled = MutableLiveData<Boolean>()
    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled

    private val _isUsernameValid = MutableLiveData<Boolean>()
    val isUsernameValid: LiveData<Boolean> = _isUsernameValid

    private val _isPasswordValid = MutableLiveData<Boolean>()
    val isPasswordValid: LiveData<Boolean> = _isPasswordValid

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    private val _loginError = MutableLiveData<Unit>()
    val loginError: LiveData<Unit> get() = _loginError

    fun onUsernameTextChanged(text: CharSequence?) {
        _usernameInput.value = text?.toString()?.trim()
        updateButtonEnabledState()
    }

    fun onPasswordTextChanged(text: CharSequence?) {
        _passwordInput.value = text?.toString()?.trim()
        updateButtonEnabledState()
    }

    private fun updateButtonEnabledState() {
        val username = _usernameInput.value
        val password = _passwordInput.value

        _isButtonEnabled.value = !username.isNullOrEmpty() && !password.isNullOrEmpty()
    }

    fun login(username: String, password: String) {
        val request = LoginRequest(username, password)
        viewModelScope.launch {
            try {
                val response: Response<LoginResponse> = withContext(Dispatchers.IO) {
                    authAPI.login(request)
                }
                if (response.isSuccessful) {
                    UserHolder.username = username

                    val loginResponse = response.body()
                    val accessToken = loginResponse?.success
                    val refreshToken = loginResponse?.token
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
