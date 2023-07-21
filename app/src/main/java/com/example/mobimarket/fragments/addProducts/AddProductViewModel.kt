package com.example.mobimarket.fragments.addProducts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobimarket.data.Product

class AddProductViewModel : ViewModel() {

    val productList = MutableLiveData<List<Product>>(emptyList())

    fun addProduct(product: Product) {
        val currentList = productList.value ?: emptyList()
        val updatedList = currentList + product
        productList.value = updatedList
    }
    fun getProductById(productId: Int?): Product? {
        return productList.value?.find { it.id == productId }
    }

}
