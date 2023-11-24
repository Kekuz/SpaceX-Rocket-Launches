package com.spacex_rocket_launches.data.network.dto.launch.launch_request_body

data class LaunchRequestBody(
    val query: LaunchQuery,
    val options: LaunchOptions,
)
