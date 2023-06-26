package com.example.mobimarket.utils

import com.example.mobimarket.data.RegisterRequest
import com.example.mobimarket.data.RegisterResponse
import com.example.mobimarket.utils.Constants.BASE_URL
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface ApiService {


    @POST("register")
    fun register(@Body registerRequest: RegisterRequest): Call<RegisterResponse>

}

//
//    fun getService(): APIConsumer {
//        val client: OkHttpClient = OkHttpClient.Builder()
//            .connectTimeout(60, TimeUnit.SECONDS)
//            .readTimeout(60, TimeUnit.SECONDS)
//            .writeTimeout(60, TimeUnit.SECONDS)
//            .build()
//
//        val builder: Retrofit.Builder = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//        val retrofit: Retrofit = builder.build()
//        return retrofit.create(APIConsumer::class.java)
//
//    }



