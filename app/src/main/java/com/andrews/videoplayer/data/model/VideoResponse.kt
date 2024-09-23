package com.andrews.videoplayer.data.model

data class VideoResponse(
    val categories: List<Category>
)

data class Category(
    val name: String,
    val videos: List<Video>
)

data class Video(
    val description: String,
    val sources: List<String>,
    val subtitle: String,
    val thumb: String,
    val title: String
)