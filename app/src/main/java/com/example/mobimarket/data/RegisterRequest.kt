package com.example.mobimarket.data

data class RegisterRequest(
    val username: String?,
    val email: String?,
    val password: String,
    val password_repeat: String
)
