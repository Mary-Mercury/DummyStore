package com.example.dummystore.data

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("products?limit=0")
    suspend fun getAllProducts(): Products
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product

    @GET("products/category-list")
    suspend fun getProductCategories(): List<String>

    @GET("products/category/{categoryName}")
    suspend fun getProductsByCategory(@Path("categoryName") category: String): Products
}
