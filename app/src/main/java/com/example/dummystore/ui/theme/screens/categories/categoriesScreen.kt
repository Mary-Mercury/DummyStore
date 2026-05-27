package com.example.dummystore.ui.theme.screens.categories

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dummystore.data.ApiResult
import com.example.dummystore.data.Products
import com.example.dummystore.ui.theme.DummyStoreTheme
import com.example.dummystore.ui.theme.screens.ErrorScreen
import com.example.dummystore.ui.theme.screens.LoadingScreen
import com.example.dummystore.ui.theme.viewmodels.MainViewModel

@Composable
fun CategoriesScreen(
    modifier: Modifier,
    navController: NavController,
    viewmodel: MainViewModel
) {
    LaunchedEffect(key1 = Unit) { viewmodel.getCategories() }

    val categories by viewmodel.categories.collectAsState()

    when(categories) {
        is ApiResult.Error -> {
            ErrorScreen(modifier, errorText = (categories as ApiResult.Error).message.toString()) {
                viewmodel.getCategories()
            }
        }
        ApiResult.Loading -> {
            LoadingScreen(modifier)
        }
        is ApiResult.Success<*> -> {
            val selectedCategories = (categories as ApiResult.Success<List<String>>).data
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 150.dp),
                modifier = modifier.padding().padding(horizontal = 4.dp)
            ) {
                items(
                    items = selectedCategories
                ) {
                    CategoriesCard(
                        category = it,
                        onClick = {
                            navController.navigate("productsByCategory/${it}")

                        }
                    )
                }
            }
        }
    }
}
