package com.spacex_rocket_launches.data.repository

import android.util.Log
import com.spacex_rocket_launches.data.NetworkClient
import com.spacex_rocket_launches.data.network.dto.LaunchSearchRequest

import com.spacex_rocket_launches.data.network.dto.LaunchSearchResponse
import com.spacex_rocket_launches.domain.api.reposiory.LaunchRepository
import com.spacex_rocket_launches.domain.models.Launch
import com.spacex_rocket_launches.domain.models.launch_request_body.RequestBody
import com.spacex_rocket_launches.util.Resource
import java.text.SimpleDateFormat
import java.util.Locale

class LaunchRepositoryImpl(private val networkClient: NetworkClient) : LaunchRepository {
    private val dateFormat =
        SimpleDateFormat("dd-MM-yyyy", Locale.US)//ДД-ММ-ГГГГ

    private val timeDateFormat =
        SimpleDateFormat("HH-mm dd-MM-yyyy", Locale.US)//ЧЧ-ММ ДД-ММ-ГГГГ

    override fun search(body: RequestBody): Resource<List<Launch>> {
        val response = networkClient.doRequest(LaunchSearchRequest(body))
        Log.e("response", response.toString())
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Check internet connection")
            }

            200 -> {
                Resource.Success((response as LaunchSearchResponse).docs.map {
                    //Нужно обязательно разобраться с типами!!!!
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
                })
            }

            else -> Resource.Error("Server error")
        }
    }

    private companion object {
        const val TO_MILLIS = 1000L
    }

}