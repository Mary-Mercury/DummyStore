package com.example.dummystore.ui.theme.screens.navigation

sealed class StartDestination {
    object Login : StartDestination()
    object Home : StartDestination()
}