package com.example.mobimarket.utils


import com.example.mobimarket.data.CodeVerificationRequest
import com.example.mobimarket.data.CodeVerificationResponse
import com.example.mobimarket.data.SendCode
import com.example.mobimarket.data.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface DataApi {
    @PUT("profile/")
    suspend fun updateUserInfo(
        @Header("Authorization") token: String,
        @Body userInfo: User
    ): Response<Any>

}