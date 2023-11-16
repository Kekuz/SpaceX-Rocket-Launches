package com.spacex_rocket_launches.di

import android.content.Context
import com.spacex_rocket_launches.data.NetworkClient
import com.spacex_rocket_launches.data.network.RetrofitNetworkClient
import com.spacex_rocket_launches.data.repository.CrewRepositoryImpl
import com.spacex_rocket_launches.data.repository.LaunchRepositoryImpl
import com.spacex_rocket_launches.domain.api.reposiory.CrewRepository
import com.spacex_rocket_launches.domain.api.reposiory.LaunchRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideNetworkClient(context: Context): NetworkClient{
        return RetrofitNetworkClient(context)
    }
    @Provides
    fun provideLaunchRepository(networkClient: NetworkClient): LaunchRepository {
        return LaunchRepositoryImpl(networkClient)
    }
    @Provides
    fun provideCrewRepository(networkClient: NetworkClient): CrewRepository {
        return CrewRepositoryImpl(networkClient)
    }

}