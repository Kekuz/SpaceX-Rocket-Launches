package com.spacex_rocket_launches.domain.api.reposiory

import com.spacex_rocket_launches.domain.models.LaunchResponse
import com.spacex_rocket_launches.domain.models.launch_request_body.LaunchRequestBody
import com.spacex_rocket_launches.util.Resource

interface LaunchRepository {
    fun search(page: Int): Resource<LaunchResponse>
}