package com.andrews.videoplayer.ui.navigation

import kotlinx.serialization.Serializable


sealed class Screen {
    @Serializable
    data object MainScreen: Screen()

    @Serializable
    data object DetailsScreen: Screen()
}