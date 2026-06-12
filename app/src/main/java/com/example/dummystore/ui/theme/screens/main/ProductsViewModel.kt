package com.example.dummystore.ui.theme.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dummystore.data.ApiResult
import com.example.dummystore.data.Product
import com.example.dummystore.data.ProductRepository
import com.example.dummystore.data.Products
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _products = MutableStateFlow<ApiResult<Products>>(ApiResult.Loading)
    val products: StateFlow<ApiResult<Products>> = _products

    fun getProducts() {
        viewModelScope.launch {
            _products.value = ApiResult.Loading
            try {
                val response = productRepository.getAllProducts()
                _products.value = ApiResult.Success(response)
            } catch (e: Exception) {
                _products.value = ApiResult.Error(e.message)
            }
        }
    }


    init {
        getProducts()
    }
}