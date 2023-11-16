package com.spacex_rocket_launches.di

import com.spacex_rocket_launches.domain.api.reposiory.CrewRepository
import com.spacex_rocket_launches.domain.api.reposiory.LaunchRepository
import com.spacex_rocket_launches.domain.api.usecase.SearchCrewUseCase
import com.spacex_rocket_launches.domain.api.usecase.SearchLaunchUseCase
import com.spacex_rocket_launches.domain.impl.SearchCrewUseCaseImpl
import com.spacex_rocket_launches.domain.impl.SearchLaunchUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideSearchLaunchUseCase(launchRepository: LaunchRepository): SearchLaunchUseCase {
        return SearchLaunchUseCaseImpl(launchRepository)
    }
    @Provides
    fun provideSearchCrewUseCase(crewRepository: CrewRepository): SearchCrewUseCase {
        return SearchCrewUseCaseImpl(crewRepository)
    }
}