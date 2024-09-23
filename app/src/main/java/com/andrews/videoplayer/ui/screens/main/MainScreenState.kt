package com.andrews.videoplayer.ui.screens.main

import com.andrews.videoplayer.domain.model.VideoFile
import java.lang.Error

data class MainScreenState(
    val allVideos: List<VideoFile> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false,
)
