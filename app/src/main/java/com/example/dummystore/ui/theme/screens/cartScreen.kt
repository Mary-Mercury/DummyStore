package com.example.dummystore.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.dummystore.ui.theme.viewmodels.MainViewModel

@Composable
fun CartScreen(
    navController: NavController,
    viewModel: MainViewModel,
    modifier: Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "This  section is unavailable now."
        )
    }
}