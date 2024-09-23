package com.andrews.videoplayer.ui.screens.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.andrews.videoplayer.domain.model.VideoFile
import com.andrews.videoplayer.ui.screens.main.components.VideoPreviewItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    navController: NavHostController
) {

    val viewModel = koinViewModel<MainScreenViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .systemBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = state.allVideos.isNotEmpty(),
            enter = fadeIn(animationSpec = tween(2000)),
            exit = fadeOut()
        ) {
            LazyColumn {
                items(state.allVideos, key = { item: VideoFile -> item.title }) { item ->
                    VideoPreviewItem(
                        thumbUrl = item.thumbUrl,
                        title = item.title,
                        subtitle = item.subtitle,
                        onClick = {
                            //TODO: Navigate to detail screen and play video
                        }
                    )
                }
            }
        }
    }
}