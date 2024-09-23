package com.andrews.videoplayer.domain

import com.andrews.videoplayer.domain.model.VideoFile
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getAllVideos(): Flow<List<VideoFile>>

    suspend fun updateData()

    suspend fun getVideoById(id: Long): VideoFile
}