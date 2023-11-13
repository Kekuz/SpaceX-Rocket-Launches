package com.spacex_rocket_launches.domain.api.reposiory

import com.spacex_rocket_launches.domain.models.Launch
import com.spacex_rocket_launches.domain.models.launch_request_body.RequestBody

interface LaunchRepository {
    fun search(body: RequestBody): List<Launch>
}