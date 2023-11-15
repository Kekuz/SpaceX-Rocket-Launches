package com.spacex_rocket_launches.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.spacex_rocket_launches.domain.models.Launch
import com.spacex_rocket_launches.domain.models.Pilot

class LaunchDetailsViewModel(private val launch: Launch): ViewModel() {

    private val _pilotsLiveData = MutableLiveData<List<Pilot>>()
    val pilotsLiveData: LiveData<List<Pilot>> = _pilotsLiveData

    init {
        doRequest()
    }

    private fun doRequest() {
        //Делаем запрос в сеть про пилотов
        _pilotsLiveData.value = listOf(
            Pilot("Douglas Hurley", "NASA", "Active"),
            Pilot("Robert Behnken", "NASA", "Active"),
        )
    }
}