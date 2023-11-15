package com.spacex_rocket_launches.presentation.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.spacex_rocket_launches.domain.api.usecase.SearchCrewUseCase
import com.spacex_rocket_launches.domain.models.Launch
import com.spacex_rocket_launches.domain.models.Pilot
import com.spacex_rocket_launches.util.Creator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LaunchDetailsViewModel(private val launch: Launch): ViewModel() {

    private val _pilotsLiveData = MutableLiveData<List<Pilot>>()
    val pilotsLiveData: LiveData<List<Pilot>> = _pilotsLiveData

    private val _placeholderLiveData = MutableLiveData<String>()
    val placeholderLiveData: LiveData<String> = _placeholderLiveData

    private val pilots = ArrayList<Pilot>()
    init {
        doRequest()
    }

    private fun doRequest() {
        if(launch.crew.isNotEmpty()){
            Creator.provideCrewInteractor()
                .execute(launch.crew, object : SearchCrewUseCase.CrewConsumer {
                    override fun consume(foundPilots: List<Pilot>?, errorMessage: String?) {
                        CoroutineScope(Dispatchers.IO).launch {
                            if (foundPilots != null) {
                                pilots.addAll(foundPilots)
                                _pilotsLiveData.postValue(pilots)
                                Log.d("Pilots", foundPilots.toString())
                            }
                            if (errorMessage != null) {
                                _placeholderLiveData.postValue(errorMessage.toString())
                            } else {
                                _placeholderLiveData.postValue("-")
                            }
                        }

                    }
                })
        }


    }
}