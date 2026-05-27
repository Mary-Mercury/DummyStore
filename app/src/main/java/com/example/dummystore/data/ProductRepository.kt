package com.example.dummystore.data

import javax.inject.Inject

class ProductRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllProducts(): Products {
        return apiService.getAllProducts()
    }

    suspend fun getProduct(id: Int): Product {
        return apiService.getProductById(id)
    }

    suspend fun getProductCategories(): List<String> {
        return apiService.getProductCategories()
    }

    suspend fun getProductsByCategory(category: String): Products {
        return apiService.getProductsByCategory(category)
    }

}
