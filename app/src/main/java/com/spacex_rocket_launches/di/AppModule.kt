package com.spacex_rocket_launches.di

import android.content.Context
import com.spacex_rocket_launches.domain.api.usecase.SearchCrewUseCase
import com.spacex_rocket_launches.domain.api.usecase.SearchLaunchUseCase
import com.spacex_rocket_launches.presentation.details.LaunchDetailsFactory
import com.spacex_rocket_launches.presentation.list.LaunchListFactory
import com.spacex_rocket_launches.util.Debounce
import dagger.Module
import dagger.Provides

@Module
class AppModule(val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }
    @Provides
    fun provideLaunchDetailsFactory(searchCrewUseCase: SearchCrewUseCase): LaunchDetailsFactory {
        return LaunchDetailsFactory(searchCrewUseCase)
    }
    @Provides
    fun provideLaunchListFactory(searchLaunchUseCase: SearchLaunchUseCase): LaunchListFactory {
        return LaunchListFactory(searchLaunchUseCase)
    }

    @Provides
    fun provideDebounce(): Debounce {
        return Debounce()
    }
}