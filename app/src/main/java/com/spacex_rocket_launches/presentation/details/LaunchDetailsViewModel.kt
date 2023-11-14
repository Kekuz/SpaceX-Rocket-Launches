package com.spacex_rocket_launches.presentation.details

import androidx.lifecycle.ViewModel
import com.spacex_rocket_launches.domain.models.Launch

class LaunchDetailsViewModel(private val launch: Launch): ViewModel() {

    init {
        doRequest()
    }

    private fun doRequest() {
        //Делаем запрос в сеть про пилотов
    }
}