package com.spacex_rocket_launches.presentation.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.spacex_rocket_launches.util.Creator
import com.spacex_rocket_launches.domain.api.LaunchInteractor
import com.spacex_rocket_launches.domain.models.Launch
import com.spacex_rocket_launches.domain.models.LaunchRequestFilter
import com.spacex_rocket_launches.domain.models.LaunchResponse
import com.spacex_rocket_launches.presentation.model.SingletonLaunch
import com.spacex_rocket_launches.presentation.model.SingletoneHasNextPageInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class LaunchListViewModel : ViewModel() {

    private val _launchesLiveData = MutableLiveData<List<Launch>>()
    val launchesLiveData: LiveData<List<Launch>> = _launchesLiveData

    private val _placeholderLiveData = MutableLiveData<String>()
    val placeholderLiveData: LiveData<String> = _placeholderLiveData


    private val launches = ArrayList<Launch>()


    init {
        doRequest()
    }

    fun doRequest() {
        Creator.provideTrackInteractor()
            .searchLaunch(LaunchRequestFilter.body, object : LaunchInteractor.LaunchConsumer {
                override fun consume(foundLaunchResponse: LaunchResponse?, errorMessage: String?) {
                    CoroutineScope(Dispatchers.IO).launch {
                        if (foundLaunchResponse != null) {
                            launches.addAll(foundLaunchResponse.docs)
                            _launchesLiveData.postValue(launches)
                            SingletoneHasNextPageInfo.hasNextPage = foundLaunchResponse.hasNextPage
                            Log.d("Launches", foundLaunchResponse.toString())
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