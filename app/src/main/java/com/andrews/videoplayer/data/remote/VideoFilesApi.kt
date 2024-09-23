package com.andrews.videoplayer.data.remote

import com.andrews.videoplayer.util.AppConsts.VIDEOS_PATH
import retrofit2.Response
import retrofit2.http.GET

interface VideoFilesApi {
    @GET(VIDEOS_PATH)
    suspend fun getVideosRaw(): Response<String>
}