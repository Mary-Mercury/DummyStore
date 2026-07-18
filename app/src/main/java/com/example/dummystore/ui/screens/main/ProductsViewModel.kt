package com.example.dummystore.ui.screens.main

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dummystore.R
import com.example.dummystore.data.ApiResult
import com.example.dummystore.data.Article
import com.example.dummystore.data.ArticlesRepository
import com.example.dummystore.data.MainUiState
import com.example.dummystore.data.Product
import com.example.dummystore.data.ProductRepository
import com.example.dummystore.data.Products
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val articleRepository: ArticlesRepository
) : ViewModel() {

    private val _uiSate = MutableStateFlow<ApiResult<MainUiState>>(ApiResult.Loading)
    val uiState: StateFlow<ApiResult<MainUiState>> = _uiSate

    private val _products = MutableStateFlow<ApiResult<Products>>(ApiResult.Loading)
    val products: StateFlow<ApiResult<Products>> = _products

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles.asStateFlow()

    fun getArticles() {
        if (_articles.value.isNotEmpty()) return
        viewModelScope.launch {
            try {
                delay(1000)
                _articles.value = articleRepository.getArticles()
            } catch (e: Exception) {
                _articles.value = emptyList()
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

    fun getUiState() {
        if (_uiSate.value is ApiResult.Success) return
        viewModelScope.launch {
            val idsProductsOfDay = listOf(123, 136, 78, 80, 100, 105, 161, 159)
            val idsBestSellers = listOf(168, 97, 6, 192, 179, 114, 123, 143)
            val idsNewProducts = listOf(79, 45, 163, 103, 157, 52, 133, 113)
            viewModelScope.launch {
                _uiSate.value = ApiResult.Loading
                try {
                    val productsOfDay = coroutineScope {
                        idsProductsOfDay.map { id ->
                            async {
                                productRepository.getProduct(id)
                            }
                        }.awaitAll()
                    }
                    val bestSellers = coroutineScope {
                        idsBestSellers.map { id ->
                            async {
                                productRepository.getProduct(id)
                            }
                        }.awaitAll()
                    }
                    val newProducts = coroutineScope {
                        idsNewProducts.map { id ->
                            async {
                                productRepository.getProduct(id)
                            }
                        }.awaitAll()
                    }
                    _uiSate.value = ApiResult.Success(
                        data = MainUiState(
                            godsOfTheDay = Products(productsOfDay.toMutableList()),
                            popularGods = Products(bestSellers.toMutableList()),
                            newGods = Products(newProducts.toMutableList())
                        )
                    )
                } catch (e: Exception) {
                    _uiSate.value = ApiResult.Error(e.message)
                }
            }
        }
    }

    init {
        getArticles()
        getUiState()
    }
}
