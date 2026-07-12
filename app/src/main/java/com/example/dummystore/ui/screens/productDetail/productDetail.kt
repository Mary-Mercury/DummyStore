package com.example.dummystore.ui.screens.productDetail

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dummystore.R
import com.example.dummystore.data.ApiResult
import com.example.dummystore.data.Dimensions
import com.example.dummystore.data.Product
import com.example.dummystore.data.Review
import com.example.dummystore.ui.screens.ErrorScreen
import com.example.dummystore.ui.screens.LoadingScreen
import com.example.dummystore.ui.screens.main.ProductsViewModel
import com.example.dummystore.ui.theme.DummyStoreTheme

@Composable
fun ProductDetail(
    modifier: Modifier = Modifier,
    productId: Int,
    viewModel: ProductDetailViewModel,
    navController: NavController
) {
    val product by viewModel.selectedProduct.collectAsState()
    LaunchedEffect(productId) { viewModel.getSelectedProduct(productId) }

    when (val result = product) {
        is ApiResult.Loading -> {
            LoadingScreen(modifier)
        }
        is ApiResult.Success -> {
            ProductDetailContent(modifier,result.data)
        }
        is ApiResult.Error -> {
            val message = (product as ApiResult.Error).message
            ErrorScreen(modifier, errorText = message.toString())
        }
    }
}

@Composable
fun ProductDetailContent(
    modifier: Modifier,
    product: Product
) {
    val pagerState = rememberPagerState(pageCount = { product.images.size })

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Description", "Specifications")

    val imgUrl = product.images.firstOrNull()
    Log.d("IMAGE_URL", imgUrl.toString())

    LazyColumn(modifier = modifier.fillMaxSize().padding()) {
        item {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxWidth(),
                pageSize = PageSize.Fill,
                key = { product.images[it] }
            ) { index ->
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth(),
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(product.images[index])
                        .crossfade(false)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    error = painterResource(R.drawable.placeholder_400x300_error),
                    placeholder = painterResource(R.drawable.placeholder_400x300)
                )
            }
        }
        item {
            Column(modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp)
            ) {
                Text(
                    text = product.title,
                    fontSize = 20.sp,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier.padding(end = 5.dp).weight(1f),) {
                        Text(
                            text = "Price: ${product.price} $",
                            fontSize = 16.sp,
                            modifier = Modifier.padding(10.dp),
                        )
                    }
                    OutlinedIconButton(
                        onClick = {},
                        modifier = Modifier.padding(horizontal = 5.dp)
                    ) {
                        Icon(Icons.Default.Favorite, "")
                    }
                    OutlinedButton(
                        onClick = {},
                        modifier = Modifier.padding(start = 5.dp)
                    ) {
                        Text(text = "buy now")
                    }
                }
                Card {
                    TabRow(
                        selectedTabIndex = selectedTabIndex
                    ) {
                        tabs.forEachIndexed { index, string ->
                            Tab(
                                selected = selectedTabIndex == index,
                                onClick = { selectedTabIndex = index },
                                text = { Text(string) }
                            )
                        }
                    }
                    when(selectedTabIndex) {
                        0 -> DescriptionTab(text = product.description)
                        1 -> SpecificationsTab(
                            brand = product.brand ?: "Unknown",
                            category = product.category,
                            rating = product.rating,
                            weight = product.weight,
                            warrantyInformation = product.warrantyInformation,
                            returnPolicy = product.returnPolicy,
                            minimumOrderQuantity = product.minimumOrderQuantity,
                            dims = product.dimensions
                        )
                    }
                }
            }
        }
        item {
            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    text = "Rating: ${product.rating}",
                    fontSize = 20.sp,
                )

                for (i in product.reviews) {
                    ReviewCard(review = i)
                }
            }
        }
    }
}

@Composable
fun DescriptionTab(text: String) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {
        Text(text = text, modifier = Modifier.padding(10.dp),
            textAlign = TextAlign.Justify)
    }
}

@Composable
fun SpecificationsTab(
    brand: String,
    category: String,
    rating: Double,
    weight: Int,
    warrantyInformation: String,
    returnPolicy: String,
    minimumOrderQuantity: Int,
    dims: Dimensions) {
    Card(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "brand: "
                )
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "category: "
                )
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "rating: "
                )
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "weight: "
                )
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "warranty: "
                )
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "return policy: "
                )
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "minimum order quantity: "
                )
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "dimentions: "
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = brand
                )
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = category
                )
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = rating.toString()
                )
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = weight.toString()
                )
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = warrantyInformation
                )
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = returnPolicy
                )
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = minimumOrderQuantity.toString()
                )
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "width: ${dims.width} \nheight: ${dims.height} \ndepth: ${dims.depth}"
                )
            }
        }
    }
}

@Composable
fun ReviewCard(review: Review) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 5.dp),
        elevation = CardDefaults.elevatedCardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .weight(1f),
                    text = review.reviewerName
                )
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    modifier = Modifier.padding(end = 10.dp),
                    text = review.rating.toString(),
                    fontSize = 20.sp
                )
            }
            OutlinedCard(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(10.dp)
                ) {
                    Text(text = review.comment)
                }
            }
        }
    }
}

@Preview
@Composable
fun showReviewCard() {
    DummyStoreTheme {
        ReviewCard(
            review = Review(
                rating = 5,
                comment = "Very satisfied!",
                date = "2024-05-23T08:56:21.618Z",
                reviewerName = "Scarlett Wright",
                reviewerEmail = "scarlett.wright@x.dummyjson.com"
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun showSpecificationsTab() {
    DummyStoreTheme {
        SpecificationsTab(
            brand = "Essence",
            category = "beauty",
            rating = 4.94,
            weight = 2,
            warrantyInformation = "1 month warranty",
            returnPolicy = "30 days return policy",
            minimumOrderQuantity = 24,
            dims = Dimensions(
                width = 23.17,
                height = 14.43,
                depth = 28.01
            )
        )
    }
}
