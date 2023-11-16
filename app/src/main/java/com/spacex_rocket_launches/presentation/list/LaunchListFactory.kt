package com.spacex_rocket_launches.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spacex_rocket_launches.domain.api.usecase.SearchLaunchUseCase

class LaunchListFactory(
    private val searchLaunchUseCase: SearchLaunchUseCase
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LaunchListViewModel(searchLaunchUseCase) as T
    }
}