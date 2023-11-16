package com.spacex_rocket_launches.app

import android.app.Application
import com.spacex_rocket_launches.di.AppComponent
import com.spacex_rocket_launches.di.AppModule
import com.spacex_rocket_launches.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()

        //Creator.initAppContext(applicationContext)
    }
}