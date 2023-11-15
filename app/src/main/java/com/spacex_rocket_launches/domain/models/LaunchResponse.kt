package com.spacex_rocket_launches.domain.models


data class LaunchResponse(
    val docs: List<Launch>,
    val hasNextPage: Boolean,
)
