package com.example.dummystore.ui.theme.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.dummystore.ui.theme.screens.CartScreen
import com.example.dummystore.ui.theme.viewmodels.MainViewModel
import com.example.dummystore.ui.theme.screens.categories.CategoriesScreen
import com.example.dummystore.ui.theme.screens.categories.ProductByCategoriesScreen
import com.example.dummystore.ui.theme.screens.ProductDetail
import com.example.dummystore.ui.theme.screens.main.ShowProducts
import com.example.dummystore.ui.theme.screens.others.OthersNav

@Composable
fun MainNavigation(
    navController: NavHostController,
    startDestination: Destinations,
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ) {
        Destinations.entries.forEach { destinations ->
            composable(destinations.route) {
                when(destinations) {
                    Destinations.PRODUCTS -> ProductsNavigation(modifier, mainViewModel)
                    Destinations.CATEGORIES -> CategoryNavigation(modifier, mainViewModel)
                    Destinations.CART -> CartScreen(navController, mainViewModel, modifier)
                    Destinations.OTHERS -> OthersNav(modifier)
                }
            }
        }
    }
}

@Composable
fun CategoryNavigation(modifier: Modifier, viewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "categories"
    ) {
        composable(
            route = "categories",
        ) {
            CategoriesScreen(modifier, navController, viewModel)
        }

        composable(
            route = "productsByCategory/{category}",
            arguments = listOf(
                navArgument("category") {type = NavType.StringType}
            ),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "'https://dummyjson.com/products/category/{category}"
                }
            )
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: return@composable
            ProductByCategoriesScreen(modifier, category, viewModel, navController)
        }

        composable(
            route = "productDetail/{productId}",
            arguments = listOf(
                navArgument("productId") { type = NavType.IntType }
            ),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "https://dummyjson.com/products/{productId}"
                }
            )) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: return@composable

            ProductDetail(modifier, productId, viewModel, navController)
        }
    }
}

@Composable
fun ProductsNavigation(modifier: Modifier, viewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "products"
    ) {
        composable(route = "products") {
            ShowProducts(modifier, navController, viewModel)
        }

        composable(
            route = "productDetail/{productId}",
            arguments = listOf(
                navArgument("productId") { type = NavType.IntType }
            ),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "https://dummyjson.com/products/{productId}"
                }
            )) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: return@composable

            ProductDetail(modifier, productId, viewModel, navController)
        }
    }
}
