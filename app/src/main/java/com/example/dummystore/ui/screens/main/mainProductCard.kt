package com.example.dummystore.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dummystore.R
import com.example.dummystore.data.Dimensions
import com.example.dummystore.data.Meta
import com.example.dummystore.data.Product
import com.example.dummystore.data.Review
import com.example.dummystore.ui.theme.DummyStoreTheme

@Composable
fun MainProductCard(
    product: Product,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.padding(5.dp).width(300.dp).height(120.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    fontSize = 12.sp,
                    text = product.title
                )
                Text(
                    fontSize = 12.sp,
                    text = product.price.toString() + "$"
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    OutlinedIconButton(
                        onClick = {},
                        modifier = Modifier.size(35.dp)
                    ) {
                        Icon(Icons.Default.ShoppingCart, "")
                    }
                    OutlinedIconButton(
                        onClick = {},
                        modifier = Modifier.size(35.dp)
                    ) {
                        Icon(Icons.Default.Star, "")
                    }
                }
            }
            Column {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(product.images[0])
                        .crossfade(false)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    error = painterResource(R.drawable.placeholder_400x300_error),
                    placeholder = painterResource(R.drawable.placeholder_400x300)
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShowMainProductCard() {
    DummyStoreTheme {
        MainProductCard(
            product = Product(
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
            onClick = {}
        )
    }
}
