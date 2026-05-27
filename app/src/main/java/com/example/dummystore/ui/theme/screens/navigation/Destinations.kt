package com.example.dummystore.ui.theme.screens.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

enum class Destinations(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDesc: String
) {
    PRODUCTS("products", "Products", Icons.Filled.Home, "Home"),
    CATEGORIES("categories", "Categories", Icons.Default.Menu, "Categories"),
    CART("cart","Cart",  Icons.Default.ShoppingCart, "Cart"),
    OTHERS("others", "Others", Icons.Default.Info, "Others")
}