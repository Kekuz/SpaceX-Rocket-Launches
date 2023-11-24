package com.spacex_rocket_launches.data.network

import com.spacex_rocket_launches.data.network.dto.crew.CrewSearchResponse
import com.spacex_rocket_launches.data.network.dto.launch.LaunchSearchResponse
import com.spacex_rocket_launches.data.network.dto.crew.crew_request_body.CrewRequestBody
import com.spacex_rocket_launches.data.network.dto.launch.launch_request_body.LaunchRequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SpaceXAPI {
    @POST("v4/launches/query")
    fun searchLaunches(
        @Body body: LaunchRequestBody
    ): Call<LaunchSearchResponse>

    @POST("v4/crew/query")
    fun searchCrew(
        @Body body: CrewRequestBody
    ): Call<CrewSearchResponse>

}