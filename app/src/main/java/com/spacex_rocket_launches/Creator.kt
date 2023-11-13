package com.spacex_rocket_launches

import com.spacex_rocket_launches.data.network.RetrofitNetworkClient
import com.spacex_rocket_launches.data.repository.LaunchRepositoryImpl
import com.spacex_rocket_launches.domain.api.LaunchInteractor
import com.spacex_rocket_launches.domain.api.reposiory.LaunchRepository
import com.spacex_rocket_launches.domain.impl.LaunchInteractorImpl

object Creator {
    private fun getTrackRepository(): LaunchRepository {
        return LaunchRepositoryImpl(RetrofitNetworkClient())
    }

    fun provideTrackInteractor(): LaunchInteractor {
        return LaunchInteractorImpl(getTrackRepository())
    }
}