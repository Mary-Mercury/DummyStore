package com.example.dummystore.ui.screens.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dummystore.data.ApiResult
import com.example.dummystore.data.ProductRepository
import com.example.dummystore.data.Products
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _categories = MutableStateFlow<ApiResult<List<String>>>(ApiResult.Loading)
    val categories: StateFlow<ApiResult<List<String>>> = _categories

    private val _categoriesProducts = MutableStateFlow<ApiResult<Products>>(ApiResult.Loading)
    val categoriesProducts: StateFlow<ApiResult<Products>> = _categoriesProducts


    fun getCategories() {
        if (_categories.value is ApiResult.Success) return
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

    private var lastCategory: String? = null

    fun getProductsByCategory(category: String) {
        if (_categoriesProducts.value is ApiResult.Success && lastCategory == category) return
        lastCategory = category
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

    init {
        getCategories()
    }
}