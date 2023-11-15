package com.spacex_rocket_launches.data.repository

import android.util.Log
import com.spacex_rocket_launches.data.NetworkClient
import com.spacex_rocket_launches.data.network.dto.launch.LaunchSearchRequest
import com.spacex_rocket_launches.data.network.dto.launch.LaunchSearchResponse
import com.spacex_rocket_launches.domain.api.reposiory.LaunchRepository
import com.spacex_rocket_launches.domain.models.Launch
import com.spacex_rocket_launches.domain.models.LaunchResponse
import com.spacex_rocket_launches.domain.models.launch_request_body.LaunchDateUtc
import com.spacex_rocket_launches.domain.models.launch_request_body.LaunchOptions
import com.spacex_rocket_launches.domain.models.launch_request_body.LaunchQuery
import com.spacex_rocket_launches.domain.models.launch_request_body.LaunchRequestBody
import com.spacex_rocket_launches.domain.models.launch_request_body.LaunchSort
import com.spacex_rocket_launches.util.Resource
import java.text.SimpleDateFormat
import java.util.Locale

class LaunchRepositoryImpl(private val networkClient: NetworkClient) : LaunchRepository {
    private val dateFormat =
        SimpleDateFormat("dd-MM-yyyy", Locale.US)//ДД-ММ-ГГГГ

    private val timeDateFormat =
        SimpleDateFormat("HH:mm dd-MM-yyyy", Locale.US)//ЧЧ-ММ ДД-ММ-ГГГГ

    override fun search(page: Int): Resource<LaunchResponse> {
        val response = networkClient.doRequest(LaunchSearchRequest(makeBody(page)))
        Log.d("response", response.toString())
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Check internet connection")
            }

            200 -> {
                Resource.Success(LaunchResponse((response as LaunchSearchResponse).docs.map {
                    Launch(
                        it.name ?: "-",
                        it.success?.toString()?.replaceFirstChar { s -> s.uppercase() } ?: "-",
                        it.links.patch.small ?: "-",
                        it.cores[0].flight?.toString() ?: "-",
                        dateFormat.format(it.date_unix * TO_MILLIS),
                        it.links.patch.large ?: "-",
                        it.details ?: "-",
                        timeDateFormat.format(it.date_unix * TO_MILLIS),
                        it.crew,
                    )
                }, response.hasNextPage))
            }

            else -> Resource.Error("Server error")
        }
    }

    private fun makeBody(page: Int): LaunchRequestBody {
        return LaunchRequestBody(
            LaunchQuery(LaunchDateUtc(MAX_DATE)),
            LaunchOptions(
                MISSIONS_ON_PAGE,
                page,
                LaunchSort(SORT_TYPE)
            )
        )
    }

    private companion object {
        const val TO_MILLIS = 1000L
        const val MAX_DATE = "2021-00-00T00:00:00.000Z"
        const val MISSIONS_ON_PAGE = 10
        const val SORT_TYPE = "desc"
    }

}