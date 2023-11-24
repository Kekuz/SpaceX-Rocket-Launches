package com.spacex_rocket_launches.domain.impl

import com.spacex_rocket_launches.domain.api.usecase.SearchLaunchUseCase
import com.spacex_rocket_launches.domain.api.reposiory.LaunchRepository
import com.spacex_rocket_launches.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchLaunchUseCaseImpl(private val repository: LaunchRepository): SearchLaunchUseCase {
    override fun execute(page: Int, consumer: SearchLaunchUseCase.LaunchConsumer) {
        CoroutineScope(Dispatchers.IO).launch {
            when(val resource = repository.search(page)) {
                is Resource.Success -> { consumer.consume(resource.data, null) }
                is Resource.Error -> { consumer.consume(null, resource.message) }
            }
        }
    }
}