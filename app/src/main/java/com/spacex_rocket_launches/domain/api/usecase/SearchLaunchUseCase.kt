package com.spacex_rocket_launches.domain.api.usecase

import com.spacex_rocket_launches.domain.models.LaunchResponse

interface SearchLaunchUseCase {
    fun execute(page: Int, consumer: LaunchConsumer)

    interface LaunchConsumer {
        fun consume(foundLaunchResponse: LaunchResponse?, errorMessage: String?)
    }
}