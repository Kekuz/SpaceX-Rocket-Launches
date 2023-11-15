package com.spacex_rocket_launches.domain.api.reposiory

import com.spacex_rocket_launches.domain.models.Launch
import com.spacex_rocket_launches.domain.models.LaunchResponse
import com.spacex_rocket_launches.domain.models.launch_request_body.RequestBody
import com.spacex_rocket_launches.util.Resource

interface LaunchRepository {
    fun search(body: RequestBody): Resource<LaunchResponse>
}