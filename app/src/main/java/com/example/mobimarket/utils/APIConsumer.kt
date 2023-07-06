package com.example.mobimarket.utils

import com.example.mobimarket.data.LoginRequest
import com.example.mobimarket.data.LoginResponse
import com.example.mobimarket.data.RegisterRequest
import kotlinx.coroutines.Deferred

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface APIConsumer : DataApi {
    @POST("token/")
    fun login(@Body requestBody: LoginRequest): Response<LoginResponse>

    @POST("register/")
    fun register(@Body request: RegisterRequest): Call<Unit>
}