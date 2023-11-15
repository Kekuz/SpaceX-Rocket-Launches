package com.spacex_rocket_launches.util

import android.content.Context
import com.spacex_rocket_launches.data.network.RetrofitNetworkClient
import com.spacex_rocket_launches.data.repository.CrewRepositoryImpl
import com.spacex_rocket_launches.data.repository.LaunchRepositoryImpl
import com.spacex_rocket_launches.domain.api.usecase.SearchCrewUseCase
import com.spacex_rocket_launches.domain.api.usecase.SearchLaunchUseCase
import com.spacex_rocket_launches.domain.api.reposiory.CrewRepository
import com.spacex_rocket_launches.domain.api.reposiory.LaunchRepository
import com.spacex_rocket_launches.domain.impl.SearchCrewUseCaseImpl
import com.spacex_rocket_launches.domain.impl.SearchLaunchUseCaseImpl

object Creator {
    private lateinit var appContext: Context
    fun initAppContext(context: Context) {
        appContext = context
    }
    private fun getLaunchRepository(): LaunchRepository {
        return LaunchRepositoryImpl(RetrofitNetworkClient(appContext))
    }

    fun provideLaunchInteractor(): SearchLaunchUseCase {
        return SearchLaunchUseCaseImpl(getLaunchRepository())
    }

    private fun getCrewRepository(): CrewRepository {
        return CrewRepositoryImpl(RetrofitNetworkClient(appContext))
    }

    fun provideCrewInteractor(): SearchCrewUseCase {
        return SearchCrewUseCaseImpl(getCrewRepository())
    }

}