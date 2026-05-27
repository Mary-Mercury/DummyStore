package com.example.dummystore.ui.theme.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dummystore.data.ApiResult
import com.example.dummystore.data.Product
import com.example.dummystore.data.ProductRepository
import com.example.dummystore.data.Products
import com.example.dummystore.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val productRepository: ProductRepository
): ViewModel() {

    private val _products = MutableStateFlow<ApiResult<Products>>(ApiResult.Loading)
    val products: StateFlow<ApiResult<Products>> = _products

    private val _selectedProduct = MutableStateFlow<ApiResult<Product>>(ApiResult.Loading)
    val selectedProduct: StateFlow<ApiResult<Product>> = _selectedProduct

    private val _categories = MutableStateFlow<ApiResult<List<String>>>(ApiResult.Loading)
    val categories: StateFlow<ApiResult<List<String>>> = _categories

    private val _categoriesProducts = MutableStateFlow<ApiResult<Products>>(ApiResult.Loading)
    val categoriesProducts: StateFlow<ApiResult<Products>> = _categoriesProducts

    fun getSelectedProduct(id: Int) {
        viewModelScope.launch {
            _selectedProduct.value = ApiResult.Loading
            try {
                val response = productRepository.getProduct(id)
                _selectedProduct.value = ApiResult.Success(response)
            } catch (e: Exception) {
                _selectedProduct.value = ApiResult.Error(e.message)
            }
        }
    }

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

    fun getProductsByCategory(category: String) {
        viewModelScope.launch {
            _categoriesProducts.value = ApiResult.Loading
            try {
                val response = productRepository.getProductsByCategory(category)
                _categoriesProducts.value = ApiResult.Success(response)
            } catch (e: Exception) {
                _categoriesProducts.value = ApiResult.Error(e.message)
            }
        }
    }

    fun getCategories() {
        viewModelScope.launch {
            _categories.value = ApiResult.Loading
            try {
                val response = productRepository.getProductCategories()
                _categories.value = ApiResult.Success(response)
            } catch (e: Exception) {
                _categories.value = ApiResult.Error(e.message)
            }
        }
    }

    init {
        getProducts()
        Log.d("fetched_data", products.value.toString())
    }
}
