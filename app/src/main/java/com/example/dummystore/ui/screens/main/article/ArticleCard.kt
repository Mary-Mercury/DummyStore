package com.example.dummystore.ui.screens.main.article

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dummystore.R
import com.example.dummystore.data.Article
import com.example.dummystore.ui.theme.DummyStoreTheme

@Composable
fun ArticleCard(
    article: Article,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier.width(200.dp).padding(5.dp),
        onClick = onClick
    ) {
        Column {
            Image(
                painter = painterResource(article.image),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                modifier = Modifier.padding(horizontal = 2.dp),
                maxLines = 2,
                text = article.title,
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.padding(vertical = 5.dp, horizontal = 2.dp),
                fontSize = 15.sp,
                text = article.description,
                textAlign = TextAlign.Justify,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun ShowArticleCard() {
    DummyStoreTheme {
        ArticleCard(
            article = Article(
                id = 0,
                title = "Android vs iOS. Which is better?",
                description = "We compared the Android and iOS operating systems and highlighted their key features.",
                content = "",
                image = R.drawable.wifi_problems
            )
        )
    }
}