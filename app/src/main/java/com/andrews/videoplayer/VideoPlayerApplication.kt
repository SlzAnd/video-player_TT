package com.andrews.videoplayer

import android.app.Application
import com.andrews.videoplayer.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class VideoPlayerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@VideoPlayerApplication)
            modules(appModule)
        }
    }
}