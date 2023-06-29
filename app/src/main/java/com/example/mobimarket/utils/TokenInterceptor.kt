package com.example.mobimarket.utils

import okhttp3.Interceptor

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
        val newRequest = if (requiresAuthorization(request)) {
            val token = UserHolder.access_token
            val authHeader = "Bearer $token"
            request.newBuilder()
                .header("Authorization", authHeader)
                .build()
        } else {
            request
        }
        return chain.proceed(newRequest)
    }

    private fun requiresAuthorization(request: okhttp3.Request): Boolean {
        return request.url.toString().endsWith("profile/")
    }
}