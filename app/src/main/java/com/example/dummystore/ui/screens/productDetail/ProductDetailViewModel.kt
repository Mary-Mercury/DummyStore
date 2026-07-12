package com.example.dummystore.ui.screens.productDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dummystore.data.ApiResult
import com.example.dummystore.data.Product
import com.example.dummystore.data.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _selectedProduct = MutableStateFlow<ApiResult<Product>>(ApiResult.Loading)
    val selectedProduct: StateFlow<ApiResult<Product>> = _selectedProduct

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

}