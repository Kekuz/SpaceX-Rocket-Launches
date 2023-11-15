package com.spacex_rocket_launches.domain.models.launch_request_body

data class LaunchRequestBody(
    val query: LaunchQuery,
    val options: LaunchOptions,
)
