package com.andrews.videoplayer.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "video_files")
data class VideoFileDto(
    @PrimaryKey(autoGenerate = false)
    val title: String,
    val subtitle: String,
    val description: String,
    val thumbUrl: String,
    val videoSourceUrl: String,
)
