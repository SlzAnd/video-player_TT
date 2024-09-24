package com.andrews.videoplayer.ui.screens.video_player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.andrews.videoplayer.domain.MainRepository
import com.andrews.videoplayer.domain.model.VideoFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VideoPlayerViewModel(
    private val repository: MainRepository,
    val exoPlayer: Player
) : ViewModel() {

    private val _state = MutableStateFlow(VideoPlayerScreenState())
    val state = _state
        .onStart { loadVideos() }
        .stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(5000L), VideoPlayerScreenState()
        )

    private fun loadVideos() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllVideos().collect { allVideos ->
                withContext(Dispatchers.Main) {
                    _state.update {
                        it.copy(
                            allVideos = allVideos
                        )
                    }
                    if (exoPlayer.mediaItemCount == 0) {
                        initializeExoPlayer(allVideos)
                    }
                }
            }
        }
    }

    private fun initializeExoPlayer(videos: List<VideoFile>) {
        val mediaItems = videos.map { MediaItem.fromUri(it.sourceUrl) }
        exoPlayer.setMediaItems(mediaItems, state.value.currentIndex, C.TIME_UNSET)
        exoPlayer.prepare()
    }

    fun setInitialIndex(index: Int) {
        if (exoPlayer.currentMediaItemIndex == 0 && exoPlayer.currentPosition == 0L) {
            _state.update {
                it.copy(
                    currentIndex = index,
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        exoPlayer.release()
    }
}