package com.example.mobimarket.utils

import android.content.SharedPreferences

object ApiAuthenticator {
    private const val TOKEN_KEY = "auth_token"
    private lateinit var sharedPreferences: SharedPreferences

    fun initialize(sharedPrefs: SharedPreferences) {
        sharedPreferences = sharedPrefs
    }
    fun saveAuthToken(token: String) {
        sharedPreferences.edit().putString(TOKEN_KEY, token).apply()
    }

    fun getAuthToken(): String? {
        return sharedPreferences.getString(TOKEN_KEY, null)
    }

    fun addAuthTokenToHeaders(headers: MutableMap<String, String>) {
        val authToken = getAuthToken()
        if (authToken != null) {
            headers["Authorization"] = "Bearer $authToken"
        }
    }
}