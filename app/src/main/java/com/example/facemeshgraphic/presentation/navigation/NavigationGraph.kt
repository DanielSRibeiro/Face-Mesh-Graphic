package com.example.facemeshgraphic.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.facemeshgraphic.presentation.screen.MainScreen
import com.example.facemeshgraphic.presentation.screen.MainViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
) {
    Scaffold { padding ->
        Box(modifier = Modifier.padding(padding)) {
            NavHost(
                navController = navController,
                startDestination = Screens.MainScreen.route
            ) {
                composable(Screens.MainScreen.route) {
                    val viewMode: MainViewModel = hiltViewModel()

                    MainScreen(
                        navController = rememberNavController(),
                        uiState = viewMode.uiState
                    )
                }
            }
        }
    }
}