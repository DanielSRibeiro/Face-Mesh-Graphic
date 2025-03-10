package com.example.facemeshgraphic.presentation.navigation

sealed class Screens(val route: String) {
    data object MainScreen : Screens(route = "main_screen")
}