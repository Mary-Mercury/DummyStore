package com.example.dummystore.ui.screens.main.article

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dummystore.data.Article
import com.example.dummystore.ui.screens.main.ProductsViewModel

@Composable
fun ArticleScreen(
    article: Article?,
    navController: NavController,
    viewmodel: ProductsViewModel
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Image(painterResource(article!!.image), contentDescription = null)
        Text(
            text = article.title,
            modifier = Modifier.padding(16.dp),
            fontSize = 24.sp
        )
        Text(
            text = article.description,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Justify
        )
        Text(
            text = article.content,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Justify
        )
    }
}
