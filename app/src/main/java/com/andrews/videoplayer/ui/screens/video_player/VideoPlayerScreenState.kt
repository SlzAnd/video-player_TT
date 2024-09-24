package com.andrews.videoplayer.ui.screens.video_player

import androidx.media3.common.C
import com.andrews.videoplayer.domain.model.VideoFile

data class VideoPlayerScreenState(
    val allVideos: List<VideoFile> = emptyList(),
    val currentIndex: Int = 0,
    val isInitialUpdate: Boolean = true,
)
