package com.example.dummystore.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dummystore.data.ApiResult
import com.example.dummystore.data.Dimensions
import com.example.dummystore.data.MainUiState
import com.example.dummystore.data.Meta
import com.example.dummystore.data.Product
import com.example.dummystore.data.Products
import com.example.dummystore.data.Review
import com.example.dummystore.ui.screens.main.article.ArticleCard
import com.example.dummystore.ui.screens.navigation.Destinations
import com.example.dummystore.ui.screens.navigation.MainTopAppBar
import com.example.dummystore.ui.theme.DummyStoreTheme

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewmodel: ProductsViewModel,
    onClickCatalog: () -> Unit
) {
    val articles by viewmodel.articles.collectAsState()
    val uiState by viewmodel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 5.dp)
            .verticalScroll(rememberScrollState())
    ) {
        when(uiState) {
            is ApiResult.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Error")
                }
            }
            ApiResult.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }
            is ApiResult.Success<*> -> {
                val godsOfTheDay = (uiState as ApiResult.Success<MainUiState>).data.godsOfTheDay.products
                val popularGods = (uiState as ApiResult.Success<MainUiState>).data.popularGods.products
                val newGods = (uiState as ApiResult.Success<MainUiState>).data.newGods.products
                PartOfScreen(
                    products = godsOfTheDay,
                    type = "Products of the Day",
                    onClick = {
                        navController.navigate("productDetail/$it")
                    }
                )
                PartOfScreen(
                    products = popularGods,
                    type = "Popular products",
                    onClick = {
                        navController.navigate("productDetail/$it")
                    }
                )
                PartOfScreen(
                    products = newGods,
                    type = "New products!",
                    onClick = {
                        navController.navigate("productDetail/$it")
                    }
                )
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp, horizontal = 5.dp)
                    ) {
                        Text(text = "Our articles!", fontWeight = FontWeight.Bold)
                    }
                    LazyRow(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(items = articles) { article ->
                            ArticleCard(
                                article = article,
                                onClick = { navController.navigate("article_screen/${article.id}") }
                            )
                        }
                    }
                }
            }
        }
        MoveToCatalog(onClickCatalog)
    }
}


@Composable
fun PartOfScreen(
    products: List<Product>,
    type: String,
    onClick: (Int) -> Unit = {}
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 5.dp)
        ) {
            Text(
                text = type,
                fontWeight = FontWeight.Bold
            )
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items = products) {
                MainProductCard (
                    product = it,
                    onClick = {
                        onClick(it.id)
                    }
                )
            }
        }
    }
}

@Composable
fun MoveToCatalog(
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "That is far from all!",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "A huge range of products in our catalog!"
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            onClick = onClick,
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "Go to the catalog!"
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShowDealsOfTheDay() {
    DummyStoreTheme {
        PartOfScreen(
            products = listOf(
                Product(
                    id = 1,
                    title = "Essence Mascara Lash Princess",
                    description = "The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.",
                    category = "beauty",
                    price = 9.99,
                    discountPercentage = 7.17,
                    rating = 4.94,
                    stock = 5,
                    tags = listOf("beauty", "mascara"),
                    brand = "Essence",
                    sku = "RCH45Q1A",
                    weight = 2,
                    dimensions = Dimensions(23.17,14.43, 28.01),
                    warrantyInformation = "1 month warranty",
                    shippingInformation = "Ships in 1 month",
                    availabilityStatus = "Low Stock",
                    reviews = listOf(
                        Review(
                            2,
                            "Very unhappy with my purchase!",
                            "2024-05-23T08:56:21.618Z",
                            "John Doe",
                            "john.doe@x.dummyjson.com"),
                    ),
                    returnPolicy = "30 days return policy",
                    minimumOrderQuantity = 24,
                    meta = Meta(
                        createdAt = "",
                        updatedAt = "",
                        barcode = "",
                        qrCode = ""
                    ),
                    thumbnail = "",
                    images = listOf("")
                ),
                Product(
                    id = 1,
                    title = "Essence Mascara Lash Princess",
                    description = "The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.",
                    category = "beauty",
                    price = 9.99,
                    discountPercentage = 7.17,
                    rating = 4.94,
                    stock = 5,
                    tags = listOf("beauty", "mascara"),
                    brand = "Essence",
                    sku = "RCH45Q1A",
                    weight = 2,
                    dimensions = Dimensions(23.17,14.43, 28.01),
                    warrantyInformation = "1 month warranty",
                    shippingInformation = "Ships in 1 month",
                    availabilityStatus = "Low Stock",
                    reviews = listOf(
                        Review(
                            2,
                            "Very unhappy with my purchase!",
                            "2024-05-23T08:56:21.618Z",
                            "John Doe",
                            "john.doe@x.dummyjson.com"),
                    ),
                    returnPolicy = "30 days return policy",
                    minimumOrderQuantity = 24,
                    meta = Meta(
                        createdAt = "",
                        updatedAt = "",
                        barcode = "",
                        qrCode = ""
                    ),
                    thumbnail = "",
                    images = listOf("")
                ),
                Product(
                    id = 1,
                    title = "Essence Mascara Lash Princess",
                    description = "The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.",
                    category = "beauty",
                    price = 9.99,
                    discountPercentage = 7.17,
                    rating = 4.94,
                    stock = 5,
                    tags = listOf("beauty", "mascara"),
                    brand = "Essence",
                    sku = "RCH45Q1A",
                    weight = 2,
                    dimensions = Dimensions(23.17,14.43, 28.01),
                    warrantyInformation = "1 month warranty",
                    shippingInformation = "Ships in 1 month",
                    availabilityStatus = "Low Stock",
                    reviews = listOf(
                        Review(
                            2,
                            "Very unhappy with my purchase!",
                            "2024-05-23T08:56:21.618Z",
                            "John Doe",
                            "john.doe@x.dummyjson.com"),
                    ),
                    returnPolicy = "30 days return policy",
                    minimumOrderQuantity = 24,
                    meta = Meta(
                        createdAt = "",
                        updatedAt = "",
                        barcode = "",
                        qrCode = ""
                    ),
                    thumbnail = "",
                    images = listOf("")
                ),
                Product(
                    id = 1,
                    title = "Essence Mascara Lash Princess",
                    description = "The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.",
                    category = "beauty",
                    price = 9.99,
                    discountPercentage = 7.17,
                    rating = 4.94,
                    stock = 5,
                    tags = listOf("beauty", "mascara"),
                    brand = "Essence",
                    sku = "RCH45Q1A",
                    weight = 2,
                    dimensions = Dimensions(23.17,14.43, 28.01),
                    warrantyInformation = "1 month warranty",
                    shippingInformation = "Ships in 1 month",
                    availabilityStatus = "Low Stock",
                    reviews = listOf(
                        Review(
                            2,
                            "Very unhappy with my purchase!",
                            "2024-05-23T08:56:21.618Z",
                            "John Doe",
                            "john.doe@x.dummyjson.com"),
                    ),
                    returnPolicy = "30 days return policy",
                    minimumOrderQuantity = 24,
                    meta = Meta(
                        createdAt = "",
                        updatedAt = "",
                        barcode = "",
                        qrCode = ""
                    ),
                    thumbnail = "",
                    images = listOf("")
                )
            ),
            type = "Товары дня"
        )
    }
}
