package com.example.cleanapistruct

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ColorsApp:Application() {
    override fun onCreate() {
        super.onCreate()

    }
}