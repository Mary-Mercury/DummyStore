package com.example.dummystore.ui.screens.productsbycategories

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
class ProductsByCategoriesViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _categoriesProducts = MutableStateFlow<ApiResult<Products>>(ApiResult.Loading)
    val categoriesProducts: StateFlow<ApiResult<Products>> = _categoriesProducts

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
}