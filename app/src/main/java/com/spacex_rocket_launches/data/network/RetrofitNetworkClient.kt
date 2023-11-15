package com.spacex_rocket_launches.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import com.spacex_rocket_launches.data.NetworkClient
import com.spacex_rocket_launches.data.network.dto.crew.CrewSearchRequest
import com.spacex_rocket_launches.data.network.dto.launch.LaunchSearchRequest
import com.spacex_rocket_launches.data.network.dto.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitNetworkClient(private val context: Context) : NetworkClient {
    private val baseUrl = "https://api.spacexdata.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val spaceXService = retrofit.create(SpaceXAPI::class.java)

    @RequiresApi(Build.VERSION_CODES.M)
    override fun doRequest(dto: Any): Response {
        if (!isConnected()) {
            return Response().apply { resultCode = -1 }
        }

        if (dto is LaunchSearchRequest) {
            val resp = spaceXService.searchLaunches(dto.body).execute()
            val body = resp.body()

            return body?.apply { resultCode = 200 } ?: Response().apply { resultCode = resp.code() }
        }
        if(dto is CrewSearchRequest){
            val resp = spaceXService.searchCrew(dto.body).execute()
            val body = resp.body()

            return body?.apply { resultCode = 200 } ?: Response().apply { resultCode = resp.code() }
        }

        return Response().apply { resultCode = 400 }




    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }
}



