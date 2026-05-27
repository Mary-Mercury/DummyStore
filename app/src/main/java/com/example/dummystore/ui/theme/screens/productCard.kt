package com.example.dummystore.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dummystore.R
import com.example.dummystore.data.Dimensions
import com.example.dummystore.data.Meta
import com.example.dummystore.data.Product
import com.example.dummystore.data.Review
import com.example.dummystore.ui.theme.DummyStoreTheme

@Composable
fun ProductCard(
    product: Product,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(5.dp).height(250.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedCard(modifier = Modifier.fillMaxWidth().padding(5.dp).weight(0.3f)) {
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
            Text(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                text = product.title,
                textAlign = TextAlign.Justify,
            )
            OutlinedCard(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp, horizontal = 15.dp),
                        text = "${product.price}$"
                    )
                }
            }
        }
    }
}
