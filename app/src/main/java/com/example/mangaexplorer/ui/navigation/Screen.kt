package com.example.mangaexplorer.ui.navigation

sealed class Screen(val route: String) {

    object Home: Screen("home")
    object Favorites: Screen("favorites")

    object Details: Screen("details/{id}") {
        fun createRoute(id: String) = "details/$id"

    }

}