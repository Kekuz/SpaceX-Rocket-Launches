package com.spacex_rocket_launches.domain.impl

import com.spacex_rocket_launches.domain.api.usecase.SearchCrewUseCase
import com.spacex_rocket_launches.domain.api.reposiory.CrewRepository
import com.spacex_rocket_launches.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchCrewUseCaseImpl(private val repository: CrewRepository): SearchCrewUseCase {
    override fun execute(ids: List<String>, consumer: SearchCrewUseCase.CrewConsumer) {
        CoroutineScope(Dispatchers.IO).launch {
            when(val resource = repository.search(ids)) {
                is Resource.Success -> { consumer.consume(resource.data, null) }
                is Resource.Error -> { consumer.consume(null, resource.message) }
            }
        }
    }
}