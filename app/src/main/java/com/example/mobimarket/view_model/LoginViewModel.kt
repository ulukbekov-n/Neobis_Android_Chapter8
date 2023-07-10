package com.example.mobimarket.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobimarket.data.LoginResponse
import com.example.mobimarket.data.TokenObtainPairRequest
import com.example.mobimarket.utils.APIConsumer
import com.example.mobimarket.utils.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val apiConsumer: APIConsumer = RetrofitInstance.apiConsumer

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    fun login(email: String, password: String) {
        val request = TokenObtainPairRequest(email, password)
        viewModelScope.launch {
            try {
                val response: Response<LoginResponse> = withContext(Dispatchers.IO) {
                    apiConsumer.login(request)
                }
                if (response.isSuccessful) {
                    _loginSuccess.value = true
                } else {
                    // Login failed
                    _loginSuccess.value = false
                }
            } catch (e: Exception) {
                _loginSuccess.value = false
            }
        }
    }
}
