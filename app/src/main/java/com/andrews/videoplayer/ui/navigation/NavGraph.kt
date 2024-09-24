package com.andrews.videoplayer.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.andrews.videoplayer.ui.screens.main.MainScreen
import com.andrews.videoplayer.ui.screens.video_player.VideoPlayerScreen


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

        composable<Screen.VideoPlayerScreen> {
            val index = it.toRoute<Screen.VideoPlayerScreen>().index
            VideoPlayerScreen(
                navController = navController,
                initialIndex = index
            )
        }
    }
}