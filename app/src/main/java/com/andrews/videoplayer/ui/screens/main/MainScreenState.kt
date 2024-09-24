package com.andrews.videoplayer.ui.screens.main

import com.andrews.videoplayer.domain.model.VideoFile

data class MainScreenState(
    val allVideos: List<VideoFile> = emptyList(),
)
