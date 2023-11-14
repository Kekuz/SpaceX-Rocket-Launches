package com.spacex_rocket_launches.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.spacex_rocket_launches.domain.models.Launch

class LaunchFactory(val launch: Launch) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LaunchDetailsViewModel(launch) as T
    }
}