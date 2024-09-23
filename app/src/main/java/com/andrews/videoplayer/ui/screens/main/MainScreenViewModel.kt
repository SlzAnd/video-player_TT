package com.andrews.videoplayer.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrews.videoplayer.domain.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MainScreenState())
    val state = combine(_state, repository.getAllVideos()) { state, videos ->
        state.copy(
            allVideos = videos,
            isLoading = false
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), MainScreenState())


    init {
        initializeData()
    }

    private fun initializeData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData()
        }
    }
}