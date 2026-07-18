package com.example.mangaexplorer.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mangaexplorer.ui.screens.DetailsScreen
import com.example.mangaexplorer.ui.screens.FavoriteScreen
import com.example.mangaexplorer.ui.screens.HomeScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {

        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(Screen.Favorites.route) {
            FavoriteScreen(onMangaClick = { mangaId ->
                navController.navigate(Screen.Details.createRoute(mangaId.toString()))

            })
        }



        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val id = backStackEntry.arguments?.getString("id") ?: ""
            DetailsScreen(id = id)

        }

    }
}