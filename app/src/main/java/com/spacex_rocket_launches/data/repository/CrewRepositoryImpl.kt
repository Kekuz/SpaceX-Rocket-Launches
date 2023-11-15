package com.spacex_rocket_launches.data.repository

import android.util.Log
import com.spacex_rocket_launches.data.NetworkClient
import com.spacex_rocket_launches.data.network.dto.crew.CrewSearchRequest
import com.spacex_rocket_launches.data.network.dto.crew.CrewSearchResponse
import com.spacex_rocket_launches.domain.api.reposiory.CrewRepository
import com.spacex_rocket_launches.domain.models.Pilot
import com.spacex_rocket_launches.domain.models.crew_request_body.CrewId
import com.spacex_rocket_launches.domain.models.crew_request_body.CrewQuery
import com.spacex_rocket_launches.domain.models.crew_request_body.CrewRequestBody
import com.spacex_rocket_launches.util.Resource

class CrewRepositoryImpl(private val networkClient: NetworkClient) : CrewRepository {

    override fun search(ids: List<String>): Resource<List<Pilot>> {
        val response = networkClient.doRequest(CrewSearchRequest(makeBody(ids)))
        Log.d("response result:", response.toString())
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Check internet connection")
            }

            200 -> {
                Resource.Success((response as CrewSearchResponse).docs.map {
                    Pilot(
                        it.name ?: "-",
                        it.agency ?: "-",
                        it.status.toString().replaceFirstChar{ s -> s.uppercase() }  ?: "-",
                    )
                })
            }

            else -> Resource.Error("Server error")
        }
    }

    private fun makeBody(ids: List<String>): CrewRequestBody{
        return CrewRequestBody(CrewQuery(CrewId(ids)))
    }
}