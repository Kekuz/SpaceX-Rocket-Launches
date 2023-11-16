package com.spacex_rocket_launches.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spacex_rocket_launches.domain.api.usecase.SearchCrewUseCase
import com.spacex_rocket_launches.domain.models.Launch

class LaunchDetailsFactory(
    private val searchCrewUseCase: SearchCrewUseCase
    ) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LaunchDetailsViewModel(searchCrewUseCase) as T
    }
}