package com.andrews.videoplayer.data

import android.util.Log
import com.andrews.videoplayer.data.local.VideoFilesDao
import com.andrews.videoplayer.data.mappers.toVideoFile
import com.andrews.videoplayer.data.mappers.toVideoFileDto
import com.andrews.videoplayer.data.remote.model.VideoResponse
import com.andrews.videoplayer.data.remote.VideoFilesApi
import com.andrews.videoplayer.domain.MainRepository
import com.andrews.videoplayer.domain.model.VideoFile
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class MainRepositoryImpl(
    private val dao: VideoFilesDao,
    private val api: VideoFilesApi
): MainRepository {

    private val TAG = "Main_Repo_Tag"
    private val gson = Gson()

    override fun getAllVideos(): Flow<List<VideoFile>> = dao
        .getAllVideoFiles()
        .map { list ->
            list.map { dto-> dto.toVideoFile() }
        }

    override suspend fun updateData(): Result<Unit> {
        try {
            val response = api.getVideosRaw()
            if (response.isSuccessful) {
                response.body()?.let { rawResponse ->
                    val jsonString = rawResponse
                        .substringAfter("var mediaJSON =")
                        .trim()
                        .removeSuffix(";")

                    try {
                        val videoResponse = gson.fromJson(jsonString, VideoResponse::class.java)
                        val videoDtos = videoResponse.categories.flatMap { it.videos.map { video -> video.toVideoFileDto() } }
                        dao.upsertAllVideoFiles(videoDtos)
                        return Result.success(Unit)
                    } catch (e: JsonSyntaxException) {
                        Log.e(TAG, "${e.message}")
                        return Result.failure(e)
                    }
                }
            } else {
                Log.e(TAG, response.code().toString())
                return Result.failure(Exception("Getting the data from API failed: status code = ${response.code()}"))
            }
        } catch (e: Exception) {
            Log.e(TAG, "${e.message}")
            return Result.failure(e)
        }
        return Result.success(Unit)
    }
}