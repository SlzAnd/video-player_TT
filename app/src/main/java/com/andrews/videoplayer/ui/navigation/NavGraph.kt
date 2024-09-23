package com.andrews.videoplayer.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.andrews.videoplayer.ui.screens.main.MainScreen


@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen
    ) {
        composable<Screen.MainScreen> {
            MainScreen(navController = navController)
        }

        composable<Screen.DetailsScreen> {

        }
    }
}