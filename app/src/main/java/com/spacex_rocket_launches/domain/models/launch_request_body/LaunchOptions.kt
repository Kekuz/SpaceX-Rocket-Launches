package com.spacex_rocket_launches.domain.models.launch_request_body

data class LaunchOptions(
    val limit: Int,
    var page: Int,
    val sort: LaunchSort,
)
