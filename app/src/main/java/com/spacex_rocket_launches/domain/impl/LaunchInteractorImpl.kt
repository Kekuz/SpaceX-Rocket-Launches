package com.spacex_rocket_launches.domain.impl

import com.spacex_rocket_launches.domain.api.LaunchInteractor
import com.spacex_rocket_launches.domain.api.reposiory.LaunchRepository
import com.spacex_rocket_launches.domain.models.launch_request_body.RequestBody
import com.spacex_rocket_launches.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LaunchInteractorImpl(private val repository: LaunchRepository): LaunchInteractor {
    override fun searchLaunch(body:RequestBody, consumer: LaunchInteractor.LaunchConsumer) {
        CoroutineScope(Dispatchers.IO).launch {
            when(val resource = repository.search(body)) {
                is Resource.Success -> { consumer.consume(resource.data, null) }
                is Resource.Error -> { consumer.consume(null, resource.message) }
            }
        }
    }
}