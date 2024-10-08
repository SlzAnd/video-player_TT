package com.andrews.videoplayer.ui.screens.main

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.andrews.videoplayer.domain.model.VideoFile
import com.andrews.videoplayer.ui.navigation.Screen
import com.andrews.videoplayer.ui.screens.main.components.VideoPreviewItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    navController: NavHostController
) {
    val viewModel = koinViewModel<MainScreenViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(viewModel.errorChannel) {
        val errMessage = viewModel.errorChannel.receive()
        Toast.makeText(context, errMessage, Toast.LENGTH_LONG).show()
    }

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
                itemsIndexed(
                    state.allVideos, key = { _: Int, item: VideoFile -> item.title }
                ) { index, item ->
                    VideoPreviewItem(
                        thumbUrl = item.thumbUrl,
                        title = item.title,
                        subtitle = item.subtitle,
                        onClick = {
                            navController.navigate(Screen.VideoPlayerScreen(index = index))
                        }
                    )
                }
            }
        }
    }
}