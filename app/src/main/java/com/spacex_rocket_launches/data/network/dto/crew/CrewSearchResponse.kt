package com.spacex_rocket_launches.data.network.dto.crew

import com.spacex_rocket_launches.data.network.dto.Response

data class CrewSearchResponse(
    val docs: List<PilotDto>
): Response()