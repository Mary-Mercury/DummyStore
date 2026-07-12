package com.example.dummystore.ui.screens.others

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun OthersNav(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "others"
    ) {
        composable(route = "others") {
            OtherScreen(navController, modifier)
        }

        composable(route = "auth") {
            AuthScreen(navController, modifier)
        }
    }
}
