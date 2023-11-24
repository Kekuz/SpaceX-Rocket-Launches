package com.spacex_rocket_launches.domain.api.usecase

import com.spacex_rocket_launches.domain.models.Pilot

interface SearchCrewUseCase {

    fun execute(ids: List<String>, consumer: CrewConsumer)

    interface CrewConsumer {
        fun consume(foundPilots: List<Pilot>?, errorMessage: String?)
    }
}