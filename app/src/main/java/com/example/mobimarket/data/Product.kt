package com.example.mobimarket.data

import android.net.Uri

data class Product(
    val id: Int,
    val name: String,
    val price: String,
    val shortDescription: String,
    val fullDescription: String,
    val imageList: List<Uri>
)
