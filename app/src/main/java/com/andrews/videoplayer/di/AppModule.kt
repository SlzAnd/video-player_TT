package com.andrews.videoplayer.di

import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.room.Room
import com.andrews.videoplayer.data.MainRepositoryImpl
import com.andrews.videoplayer.data.local.VideoFilesDao
import com.andrews.videoplayer.data.local.VideoFilesDatabase
import com.andrews.videoplayer.data.remote.VideoFilesApi
import com.andrews.videoplayer.domain.MainRepository
import com.andrews.videoplayer.ui.screens.main.MainScreenViewModel
import com.andrews.videoplayer.ui.screens.video_player.VideoPlayerViewModel
import com.andrews.videoplayer.util.AppConsts.VIDEO_API_BASE_URL
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory


val appModule = module {
    single<VideoFilesDatabase> {
        Room.databaseBuilder(
            this.androidContext(),
            VideoFilesDatabase::class.java,
            VideoFilesDatabase.DATABASE_NAME
        )
            .build()
    }

    single<VideoFilesDao> {
        get<VideoFilesDatabase>().dao()
    }

    single<Player> {
        ExoPlayer.Builder(androidContext()).build()
    }

    single<VideoFilesApi> {
        Retrofit.Builder()
            .baseUrl(VIDEO_API_BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(VideoFilesApi::class.java)
    }

    single<MainRepository> {
        MainRepositoryImpl(get(), get())
    }

    viewModel {
        MainScreenViewModel(get())
    }

    viewModel {
        VideoPlayerViewModel(get(), get())
    }
}