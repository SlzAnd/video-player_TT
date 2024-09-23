package com.andrews.videoplayer.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.andrews.videoplayer.data.model.VideoFileDto
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoFilesDao {

    @Upsert
    suspend fun upsertAllVideoFiles(recipes: List<VideoFileDto>)

    @Query("SELECT * FROM video_files")
    fun getAllVideoFiles(): Flow<List<VideoFileDto>>

    @Query("SELECT * FROM video_files WHERE title = :title")
    fun getVideoFileByTitle(title: String): VideoFileDto
}