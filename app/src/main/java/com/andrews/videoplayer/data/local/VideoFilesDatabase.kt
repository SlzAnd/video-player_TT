package com.andrews.videoplayer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andrews.videoplayer.data.model.VideoFileDto


@Database(
    entities = [VideoFileDto::class],
    version = 1,
    exportSchema = false
)
abstract class VideoFilesDatabase : RoomDatabase() {

    abstract fun dao(): VideoFilesDao

    companion object {
        const val DATABASE_NAME = "videos.db"
    }
}