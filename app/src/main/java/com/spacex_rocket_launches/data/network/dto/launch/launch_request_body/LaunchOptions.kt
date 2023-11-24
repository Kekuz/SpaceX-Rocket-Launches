package com.spacex_rocket_launches.data.network.dto.launch.launch_request_body

data class LaunchOptions(
    val limit: Int,
    var page: Int,
    val sort: LaunchSort,
)
