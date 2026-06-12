package com.example.dummystore.ui.theme.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.dummystore.ui.theme.screens.cart.CartScreen
import com.example.dummystore.ui.theme.screens.categories.CategoriesScreen
import com.example.dummystore.ui.theme.screens.categories.CategoriesViewModel
import com.example.dummystore.ui.theme.screens.main.ProductsViewModel
import com.example.dummystore.ui.theme.screens.productsbycategories.ProductByCategoriesScreen
import com.example.dummystore.ui.theme.screens.productDetail.ProductDetail
import com.example.dummystore.ui.theme.screens.productDetail.ProductDetailViewModel
import com.example.dummystore.ui.theme.screens.main.ShowProducts
import com.example.dummystore.ui.theme.screens.others.OthersNav
import com.example.dummystore.ui.theme.screens.productsbycategories.ProductsByCategoriesViewModel

@Composable
fun MainNavigation(
    navController: NavHostController,
    startDestination: Destinations,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ) {
        Destinations.entries.forEach { destinations ->
            composable(destinations.route) {
                when(destinations) {
                    Destinations.PRODUCTS -> ProductsNavigation(modifier)
                    Destinations.CATEGORIES -> CategoryNavigation(modifier)
                    Destinations.CART -> CartScreen(navController, modifier)
                    Destinations.OTHERS -> OthersNav(modifier)
                }
            }
        }
    }
}

@Composable
fun CategoryNavigation(modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "categories"
    ) {
        composable(
            route = "categories",
        ) {
            val viewModel = hiltViewModel<CategoriesViewModel>()
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
            val viewModel = hiltViewModel<ProductsByCategoriesViewModel>()
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
            val viewModel = hiltViewModel<ProductDetailViewModel>()
            ProductDetail(modifier, productId, viewModel, navController)
        }
    }
}

@Composable
fun ProductsNavigation(modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "products"
    ) {
        composable(route = "products") {
            val viewModel = hiltViewModel<ProductsViewModel>()
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
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: return@composable
            val viewModel = hiltViewModel<ProductDetailViewModel>()
            ProductDetail(modifier, productId, viewModel, navController)
        }
    }
}