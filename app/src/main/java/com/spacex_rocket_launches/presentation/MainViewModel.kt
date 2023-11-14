package com.spacex_rocket_launches.presentation

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.spacex_rocket_launches.Creator
import com.spacex_rocket_launches.domain.api.LaunchInteractor
import com.spacex_rocket_launches.domain.models.Launch
import com.spacex_rocket_launches.domain.models.LaunchRequestFilter

open class MainViewModel : ViewModel() {

    private val _launchesLiveData by lazy {
        MutableLiveData<List<Launch>>()
    }
    val launchesLiveData: LiveData<List<Launch>> = _launchesLiveData


    private val handler = Handler(Looper.getMainLooper())
    private var detailsRunnable: Runnable? = null

    private val launches = ArrayList<Launch>()


    init {
        doRequest()
    }

    private fun doRequest() {
        Creator.provideTrackInteractor()
            .searchLaunch(LaunchRequestFilter.body, object : LaunchInteractor.LaunchConsumer {
                override fun consume(foundLaunches: List<Launch>) {
                    Log.e("Launches", foundLaunches.toString())
                    val currentRunnable = detailsRunnable
                    if (currentRunnable != null) {
                        handler.removeCallbacks(currentRunnable)
                    }
                    val newDetailsRunnable = Runnable {
                        launches.addAll(foundLaunches)
                        _launchesLiveData.value = launches
                    }
                    detailsRunnable = newDetailsRunnable
                    handler.post(newDetailsRunnable)
                }
            })
    }
}