package com.spacex_rocket_launches.data.network

import com.spacex_rocket_launches.data.network.dto.LaunchSearchResponse
import com.spacex_rocket_launches.domain.models.launch_request_body.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SpaceXAPI {
    @POST("v4/launches/query")
    fun search(
        @Body body: RequestBody
    ): Call<LaunchSearchResponse>

}