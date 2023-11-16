package com.spacex_rocket_launches.presentation.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.spacex_rocket_launches.domain.api.usecase.SearchLaunchUseCase
import com.spacex_rocket_launches.domain.models.Launch
import com.spacex_rocket_launches.domain.models.LaunchResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class LaunchListViewModel(
    private val searchLaunchUseCase: SearchLaunchUseCase
) : ViewModel() {

    private val _launchesLiveData = MutableLiveData<List<Launch>>()
    val launchesLiveData: LiveData<List<Launch>> = _launchesLiveData

    private val _placeholderLiveData = MutableLiveData<String>()
    val placeholderLiveData: LiveData<String> = _placeholderLiveData


    private val launches = ArrayList<Launch>()
    var sending = false
    var hasNextPage = true
    private var pageNumber = 1


    init {
        doRequest()
    }

    fun doRequest() {
        sending = true
        searchLaunchUseCase
            .execute(pageNumber, object : SearchLaunchUseCase.LaunchConsumer {
                override fun consume(foundLaunchResponse: LaunchResponse?, errorMessage: String?) {
                    CoroutineScope(Dispatchers.IO).launch {
                        if (foundLaunchResponse != null) {
                            Log.d("Launches", foundLaunchResponse.toString())
                            Log.e("Page", "Loaded page $pageNumber")
                            launches.addAll(foundLaunchResponse.docs)
                            _launchesLiveData.postValue(launches)
                            hasNextPage = foundLaunchResponse.hasNextPage
                            if (hasNextPage) pageNumber++
                        }
                        if (errorMessage != null) {
                            _placeholderLiveData.postValue(errorMessage.toString())
                        } else {
                            _placeholderLiveData.postValue("-")
                        }
                        sending = false
                    }

                }
            })
    }
}