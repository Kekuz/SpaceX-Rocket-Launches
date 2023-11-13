package com.spacex_rocket_launches.domain.models.launch_request_body

import com.google.gson.annotations.SerializedName

data class DateUtc(
    @SerializedName("\$gt")
    val gt: String
)
