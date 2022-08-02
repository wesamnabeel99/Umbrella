package com.example.tomorrowweather.model.network

import android.util.Log
import com.example.tomorrowweather.model.response.WeatherResponse
import com.example.tomorrowweather.utils.Constants
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

object Client {
    private val okHttpClient = OkHttpClient()

    lateinit var weatherResponse: WeatherResponse


    fun requestWeatherData(onSuccess: (isSuccess: Boolean) -> Unit) {

        val httpUrl = buildHttpUrl()

        val request = Request.Builder()
            .url(httpUrl)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i("CLIENT", e.message.toString())
                onSuccess(false)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let { jsonString ->
                    weatherResponse = Gson().fromJson(jsonString, WeatherResponse::class.java)
                }
                onSuccess(true)
            }
        }
        )
    }

    private fun buildHttpUrl() = HttpUrl.Builder()
        .scheme(Constants.SCHEME)
        .host(Constants.BASE_URL)
        .addPathSegments(Constants.PATH_SEGMENTS)
        .addQueryParameter(Constants.QueryKeys.APP_ID, Constants.QueryValues.APP_ID)
        .addQueryParameter(Constants.QueryKeys.LAT, Constants.QueryValues.IRAQ_LAT)
        .addQueryParameter(Constants.QueryKeys.LON, Constants.QueryValues.IRAQ_LON)
        .addQueryParameter(Constants.QueryKeys.UNITS, Constants.QueryValues.UNITS)
        .build()
}


