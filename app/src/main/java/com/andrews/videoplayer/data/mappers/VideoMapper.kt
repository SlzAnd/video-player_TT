package com.andrews.videoplayer.data.mappers

import com.andrews.videoplayer.data.model.VideoFileDto
import com.andrews.videoplayer.data.model.Video
import com.andrews.videoplayer.domain.model.VideoFile

fun VideoFileDto.toVideoFile(): VideoFile {
    return VideoFile(
        title = this.title,
        subtitle = this.subtitle,
        thumbUrl = this.thumbUrl,
        sourceUrl = this.videoSourceUrl
    )
}

fun Video.toVideoFileDto(): VideoFileDto {
    return VideoFileDto(
        title = this.title,
        subtitle = this.subtitle,
        description = this.description,
        thumbUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/${this.thumb}",
        videoSourceUrl = this.sources[0]
    )
}