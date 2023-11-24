package com.spacex_rocket_launches.domain.api.reposiory

import com.spacex_rocket_launches.domain.models.Pilot
import com.spacex_rocket_launches.util.Resource


interface CrewRepository {
    fun search(ids: List<String>): Resource<List<Pilot>>
}