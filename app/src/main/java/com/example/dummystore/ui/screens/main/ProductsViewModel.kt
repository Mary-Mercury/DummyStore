package com.example.dummystore.ui.screens.main

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dummystore.R
import com.example.dummystore.data.ApiResult
import com.example.dummystore.data.Article
import com.example.dummystore.data.ArticlesRepository
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
    private val _products = MutableStateFlow<ApiResult<Products>>(ApiResult.Loading)
    val products: StateFlow<ApiResult<Products>> = _products

    private val _prodOfTheDay = MutableStateFlow<ApiResult<Products>>(ApiResult.Loading)
    val prodOfTheDay: StateFlow<ApiResult<Products>> = _prodOfTheDay

    private val _bestSellers = MutableStateFlow<ApiResult<Products>>(ApiResult.Loading)
    val bestSellers: StateFlow<ApiResult<Products>> = _bestSellers

    private val _newProducts = MutableStateFlow<ApiResult<Products>>(ApiResult.Loading)
    val newProducts: StateFlow<ApiResult<Products>> = _newProducts

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles.asStateFlow()

    fun getProdOfTheDay() {
        viewModelScope.launch {
            _prodOfTheDay.value = ApiResult.Loading
            val ids = listOf(123, 136, 78, 80, 100, 105, 161, 159)
            try {
                val products = coroutineScope {
                    ids.map { id ->
                        async {
                            productRepository.getProduct(id)
                        }
                    }.awaitAll()
                }
                _prodOfTheDay.value = ApiResult.Success(
                    Products(products.toMutableList())
                )
            } catch (e: Exception) {
                _prodOfTheDay.value = ApiResult.Error(e.message)
            }
        }
    }

    fun getBestSellers() {
        if (_bestSellers.value is ApiResult.Success) return
        viewModelScope.launch {
            _bestSellers.value = ApiResult.Loading
            val ids = listOf(168, 97, 6, 192, 179, 114, 123, 143)
            try {
                val products = coroutineScope {
                    ids.map { id ->
                        async {
                            productRepository.getProduct(id)
                        }
                    }.awaitAll()
                }
                _bestSellers.value = ApiResult.Success(
                    Products(products.toMutableList())
                )
            } catch (e: Exception) {
                _bestSellers.value = ApiResult.Error(e.message)
            }
        }
    }

    fun getNewProducts() {
        if (_newProducts.value is ApiResult.Success) return
        viewModelScope.launch {
            _newProducts.value = ApiResult.Loading
            val ids = listOf(79, 45, 163, 103, 157, 52, 133, 113)
            try {
                val products = coroutineScope {
                    ids.map { id ->
                        async {
                            productRepository.getProduct(id)
                        }
                    }.awaitAll()
                }
                _newProducts.value = ApiResult.Success(
                    Products(products.toMutableList())
                )
            } catch (e: Exception) {
                _newProducts.value = ApiResult.Error(e.message)
            }
        }
    }

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

    init {
        getProdOfTheDay()
        getBestSellers()
        getNewProducts()
        getArticles()
    }
}
