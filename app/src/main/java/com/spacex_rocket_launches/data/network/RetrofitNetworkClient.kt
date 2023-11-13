package com.spacex_rocket_launches.data.network

import com.spacex_rocket_launches.data.NetworkClient
import com.spacex_rocket_launches.data.network.dto.LaunchSearchRequest
import com.spacex_rocket_launches.data.network.dto.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class RetrofitNetworkClient : NetworkClient {
    private val baseUrl = "https://api.spacexdata.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val spaceXService = retrofit.create(SpaceXAPI::class.java)

    override fun doRequest(dto: Any): Response {
        try {
            if (dto is LaunchSearchRequest) {
                val resp = spaceXService.search(dto.body).execute()

                val body = resp.body() ?: Response()

                return body.apply { resultCode = 200 }
            } else {
                return Response().apply { resultCode = 400 }
            }
        }catch (e: Exception){
            return Response().apply { resultCode = 404 }
        }

    }

}