package com.example.dummystore.ui.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dummystore.data.ApiResult
import com.example.dummystore.data.Products
import com.example.dummystore.ui.screens.ErrorScreen
import com.example.dummystore.ui.screens.LoadingScreen
import com.example.dummystore.ui.screens.ProductCard

@Composable
fun ShowProducts(
    modifier: Modifier,
    navController: NavController,
    viewmodel: ProductsViewModel
) {
    val productItems by viewmodel.products.collectAsState()

    when (productItems) {
        is ApiResult.Error -> {
            val message = (productItems as ApiResult.Error).message
            ErrorScreen(modifier, message.toString()) { viewmodel.getProducts() }
        }
        ApiResult.Loading -> {
            LoadingScreen(modifier)
        }
        is ApiResult.Success<*> -> {
            val products = (productItems as ApiResult.Success<Products>).data.products
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = modifier.padding().padding(horizontal = 4.dp)
            ) {
                items(
                    items = products
                ) {
                    ProductCard(
                        product = it,
                        onClick = {
                            navController.navigate("productDetail/${it.id}")
                        }
                    )
                }
            }
        }
    }
}
