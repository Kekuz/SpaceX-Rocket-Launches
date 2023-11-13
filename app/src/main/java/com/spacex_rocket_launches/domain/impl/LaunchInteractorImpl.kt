package com.spacex_rocket_launches.domain.impl

import com.spacex_rocket_launches.domain.api.LaunchInteractor
import com.spacex_rocket_launches.domain.api.reposiory.LaunchRepository
import com.spacex_rocket_launches.domain.models.launch_request_body.RequestBody
import java.util.concurrent.Executors

class LaunchInteractorImpl(private val repository: LaunchRepository): LaunchInteractor {
    private val executor = Executors.newCachedThreadPool()
    override fun searchLaunch(body:RequestBody, consumer: LaunchInteractor.LaunchConsumer) {
        executor.execute {
            consumer.consume(repository.search(body))
        }
    }
}