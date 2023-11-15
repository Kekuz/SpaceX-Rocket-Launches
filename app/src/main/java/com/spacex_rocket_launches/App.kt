package com.spacex_rocket_launches

import android.app.Application
import com.spacex_rocket_launches.util.Creator

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Creator.initAppContext(applicationContext)
    }
}