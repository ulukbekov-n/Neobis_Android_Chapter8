package com.example.mobimarket.data

import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("confirm_password")
    val confirm_password: String,
)
