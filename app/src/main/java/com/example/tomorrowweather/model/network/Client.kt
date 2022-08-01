package com.example.tomorrowweather.model.network

import android.util.Log
import com.example.tomorrowweather.model.response.WeatherResponse
import com.example.tomorrowweather.utils.Constants
import okhttp3.*
import java.io.IOException

object Client {
    private val okHttpClient = OkHttpClient()

    lateinit var weatherResponse :WeatherResponse


    fun requestWeatherData(onSuccess: (isSuccess: Boolean) -> Unit) {

        val httpUrl = buildHttpUrl()

        val request = Request.Builder()
            .url(httpUrl)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i("CLIENT", e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let { jsonString ->
                    Log.i("CLIENT", jsonString)
                }
                onSuccess(true)
            }
        }
        )
    }

    private fun buildHttpUrl() = HttpUrl.Builder()
        .scheme("https")
        .host(Constants.BASE_URL)
        .addPathSegments("data/2.5/forecast")
        .addQueryParameter("appid", Constants.API_KEY)
        .addQueryParameter("lat", Constants.IRAQ_LAT)
        .addQueryParameter("lon", Constants.IRAQ_LON)
        .addQueryParameter("units", Constants.UNITS)
        .build()
}


