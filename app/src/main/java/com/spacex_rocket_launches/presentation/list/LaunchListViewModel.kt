package com.spacex_rocket_launches.presentation.list

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.spacex_rocket_launches.util.Creator
import com.spacex_rocket_launches.domain.api.LaunchInteractor
import com.spacex_rocket_launches.domain.models.Launch
import com.spacex_rocket_launches.domain.models.LaunchRequestFilter

open class LaunchListViewModel : ViewModel() {

    private val _launchesLiveData by lazy {
        MutableLiveData<List<Launch>>()
    }
    val launchesLiveData: LiveData<List<Launch>> = _launchesLiveData

    private val _placeholderLiveData by lazy {
        MutableLiveData<String>()
    }
    val placeholderLiveData: LiveData<String> = _placeholderLiveData

    private val handler = Handler(Looper.getMainLooper())
    private var detailsRunnable: Runnable? = null

    private val launches = ArrayList<Launch>()
    lateinit var currentLaunch: Launch


    init {
        doRequest()
    }

    private fun doRequest() {
        Creator.provideTrackInteractor()
            .searchLaunch(LaunchRequestFilter.body, object : LaunchInteractor.LaunchConsumer {
                override fun consume(foundLaunches: List<Launch>?, errorMessage: String?) {
                    //Log.e("Launches", foundLaunches.toString())
                    val currentRunnable = detailsRunnable
                    if (currentRunnable != null) {
                        handler.removeCallbacks(currentRunnable)
                    }
                    val newDetailsRunnable = Runnable {

                        if (foundLaunches != null) {
                            launches.addAll(foundLaunches)
                            _launchesLiveData.value = launches
                        }
                        if (errorMessage != null) {
                            _placeholderLiveData.value = errorMessage.toString()
                        } else {
                            _placeholderLiveData.value = "-"
                        }
                    }
                    detailsRunnable = newDetailsRunnable
                    handler.post(newDetailsRunnable)
                }
            })
    }
}