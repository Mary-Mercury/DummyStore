package com.example.dummystore.data

import com.example.dummystore.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticlesRepository @Inject constructor() {

    private val articles = listOf(
        Article(
            id = 1,
            title = "Android vs iOS. Which is better?",
            description = "We compared the Android and iOS operating systems and highlighted their key features.",
            content = "",
            image = R.drawable.placeholder_400x300
        ),
        Article(
            id = 2,
            title = "Android vs iOS. Which is better?",
            description = "We compared the Android and iOS operating systems and highlighted their key features.",
            content = "",
            image = R.drawable.placeholder_400x300
        ),
        Article(
            id = 3,
            title = "Android vs iOS. Which is better?",
            description = "We compared the Android and iOS operating systems and highlighted their key features.",
            content = "",
            image = R.drawable.placeholder_400x300
        ),
        Article(
            id = 4,
            title = "Android vs iOS. Which is better?",
            description = "We compared the Android and iOS operating systems and highlighted their key features.",
            content = "",
            image = R.drawable.placeholder_400x300
        ),
        Article(
            id = 5,
            title = "Android vs iOS. Which is better?",
            description = "We compared the Android and iOS operating systems and highlighted their key features.",
            content = "",
            image = R.drawable.placeholder_400x300
        ),
        Article(
            id = 6,
            title = "Android vs iOS. Which is better?",
            description = "We compared the Android and iOS operating systems and highlighted their key features.",
            content = "",
            image = R.drawable.placeholder_400x300
        )
    )

    fun getArticles(): List<Article> = articles

    fun getArticle(id: Int): Article? =
        articles.find { it.id == id }
}