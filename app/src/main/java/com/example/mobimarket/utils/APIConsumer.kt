package com.example.mobimarket.utils

import com.example.mobimarket.data.TokenObtainPairRequest
import com.example.mobimarket.data.LoginResponse
import com.example.mobimarket.data.RegisterRequest

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface APIConsumer : DataApi {
    @POST("/token/")
    fun login(@Body requestBody: TokenObtainPairRequest): Response<LoginResponse>

    @POST("register/")
    fun register(@Body request: RegisterRequest): Call<Unit>
}