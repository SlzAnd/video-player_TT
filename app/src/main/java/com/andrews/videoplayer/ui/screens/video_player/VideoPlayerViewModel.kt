package com.andrews.videoplayer.ui.screens.video_player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.andrews.videoplayer.domain.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VideoPlayerViewModel(
    private val repository: MainRepository,
    val exoPlayer: Player
): ViewModel() {

    private val _state = MutableStateFlow(VideoPlayerScreenState())
    val state = _state.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllVideos().collect { allVideos ->
                withContext(Dispatchers.Main) {
                    exoPlayer.apply {
                        val mediaItems = allVideos.map {
                            MediaItem.fromUri(it.sourceUrl)
                        }
                        setMediaItems(mediaItems, state.value.currentIndex, C.TIME_UNSET)
                        prepare()
                    }

                    _state.update {
                        it.copy(
                            allVideos = allVideos
                        )
                    }
                }
            }
        }
    }

    fun changeIndex(index: Int) {
        if (state.value.isInitialUpdate) {
            _state.update {
                it.copy(
                    currentIndex = index,
                    isInitialUpdate = false
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        exoPlayer.release()
    }
}