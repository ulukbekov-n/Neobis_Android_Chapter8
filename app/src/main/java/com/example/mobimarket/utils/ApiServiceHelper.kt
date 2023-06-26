package com.example.mobimarket.utils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiServiceHelper {
    private const val BASE_URL = "165.232.115.38:8000/api/"//

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
