package com.example.dummystore.data

import androidx.compose.ui.graphics.painter.Painter

data class Article(
    val id: Int,
    val title: String,
    val description: String,
    val content: String,
    val image: Int
)
