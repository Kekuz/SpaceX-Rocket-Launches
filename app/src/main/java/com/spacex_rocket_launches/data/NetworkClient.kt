package com.spacex_rocket_launches.data

import com.spacex_rocket_launches.data.network.dto.Response

interface NetworkClient {
    fun doRequest(dto: Any): Response
}