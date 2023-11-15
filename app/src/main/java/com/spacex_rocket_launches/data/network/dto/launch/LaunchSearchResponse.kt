package com.spacex_rocket_launches.data.network.dto.launch

import com.spacex_rocket_launches.data.network.dto.Response

data class LaunchSearchResponse(
    val docs: List<LaunchDto>,
    val totalDocs: Int,
    val limit: Int,
    val totalPages: Int,
    val page: Int,
    val pagingCounter: Int,
    val hasPrevPage: Boolean,
    val hasNextPage: Boolean,
    val prevPage: Int,
    val nextPage: Int,
) : Response()