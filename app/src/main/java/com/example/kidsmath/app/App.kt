package com.example.kidsmath.app

import android.app.Application
import com.example.kidsmath.data.shp.MySharedPreference
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MySharedPreference.init(this)
    }
}