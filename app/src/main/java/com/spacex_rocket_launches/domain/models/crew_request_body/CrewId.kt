package com.spacex_rocket_launches.domain.models.crew_request_body

import com.google.gson.annotations.SerializedName

data class CrewId(
    @SerializedName("\$in")
    val _in: List<String>
)
