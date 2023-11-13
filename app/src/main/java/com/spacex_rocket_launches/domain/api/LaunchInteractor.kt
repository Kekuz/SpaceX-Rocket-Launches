package com.spacex_rocket_launches.domain.api

import com.spacex_rocket_launches.domain.models.Launch
import com.spacex_rocket_launches.domain.models.launch_request_body.RequestBody

interface LaunchInteractor {
    fun searchLaunch(body: RequestBody, consumer: LaunchConsumer)

    interface LaunchConsumer {
        fun consume(foundLaunches: List<Launch>)
    }
}