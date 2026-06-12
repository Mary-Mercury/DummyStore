package com.example.dummystore.ui.theme.screens.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomBar(
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()

    val startDestination = Destinations.PRODUCTS

    var selectedDestination by rememberSaveable { mutableStateOf(startDestination.ordinal) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                Destinations.entries.forEachIndexed {index, destinations ->
                    NavigationBarItem(
                        selected = selectedDestination == index,
                        onClick = {
                            navController.navigate(route = destinations.route)
                            selectedDestination = index
                        },
                        icon = {
                            Icon(
                                destinations.icon,
                                destinations.contentDesc
                            )
                        },
                        label = { Text(destinations.label) }
                    )
                }
            }
        }
    ) {
        MainNavigation(
            navController,
            startDestination,
            Modifier.padding(it)
        )
    }
}
