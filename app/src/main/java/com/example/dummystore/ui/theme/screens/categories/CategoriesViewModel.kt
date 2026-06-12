package com.example.dummystore.ui.theme.screens.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dummystore.data.ApiResult
import com.example.dummystore.data.ProductRepository
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
}