package com.example.dummystore.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.example.dummystore.data.ArticlesRepository
import com.example.dummystore.ui.screens.cart.CartScreen
import com.example.dummystore.ui.screens.categories.CategoriesScreen
import com.example.dummystore.ui.screens.categories.CategoriesViewModel
import com.example.dummystore.ui.screens.main.ProductsViewModel
import com.example.dummystore.ui.screens.main.StartScreen
import com.example.dummystore.ui.screens.main.article.ArticleScreen
import com.example.dummystore.ui.screens.others.AuthScreen
import com.example.dummystore.ui.screens.others.OtherScreen
import com.example.dummystore.ui.screens.productDetail.ProductDetail
import com.example.dummystore.ui.screens.productDetail.ProductDetailViewModel
import com.example.dummystore.ui.screens.categories.ProductByCategoriesScreen

@Composable
fun NestedNavigation(
    navController: NavHostController,
    startDestination: Destinations,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = modifier
    ) {
        homeGraph(navController, onClick)
        categoriesGraph(navController)

        composable(Destinations.CART.route) {
            CartScreen()
        }

        othersGraph(navController)

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
            ProductDetail(productId = productId, viewModel = viewModel, navController = navController)
        }
    }
}

fun NavGraphBuilder.othersGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = "others",
        route = Destinations.OTHERS.route
    ) {
        composable(route = "others") {
            OtherScreen(navController)
        }

        composable(route = "auth") {
            AuthScreen(navController)
        }
    }
}

fun NavGraphBuilder.categoriesGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = "categories",
        route = Destinations.CATEGORIES.route
    ) {
        composable(
            route = "categories",
        ) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(Destinations.CATEGORIES.route)
            }
            val parentViewModel = hiltViewModel<CategoriesViewModel>(parentEntry)
            CategoriesScreen(navController = navController, viewmodel = parentViewModel)
        }

        composable(
            route = "productsByCategory/{category}",
            arguments = listOf(
                navArgument("category") {type = NavType.StringType}
            ),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "https://dummyjson.com/products/category/{category}"
                }
            )
        ) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(Destinations.CATEGORIES.route)
            }
            val parentViewModel = hiltViewModel<CategoriesViewModel>(parentEntry)
            val category = backStackEntry.arguments?.getString("category") ?: return@composable
            ProductByCategoriesScreen(category = category, viewmodel = parentViewModel, navController = navController)
        }
    }
}

fun NavGraphBuilder.homeGraph(
    navController: NavHostController,
    onClick: () -> Unit
) {
    navigation(
        startDestination = "main_screen",
        route = Destinations.PRODUCTS.route
    ) {
        composable("main_screen") { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(Destinations.PRODUCTS.route)
            }
            val parentViewModel = hiltViewModel<ProductsViewModel>(parentEntry)

            StartScreen(
                navController = navController,
                viewmodel = parentViewModel,
                onClickCatalog = onClick
            )
        }

        composable(
            route = "article_screen/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(Destinations.PRODUCTS.route)
            }
            val parentViewModel = hiltViewModel<ProductsViewModel>(parentEntry)

            val id = backStackEntry.arguments?.getInt("id") ?: return@composable
            val article = ArticlesRepository().getArticle(id)

            ArticleScreen(
                navController = navController,
                viewmodel = parentViewModel,
                article = article
            )
        }
    }
}
