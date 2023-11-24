package com.spacex_rocket_launches.data.network.dto.launch.launch_request_body

import com.google.gson.annotations.SerializedName

data class LaunchDateUtc(
    @SerializedName("\$gt")
    val gt: String
)
