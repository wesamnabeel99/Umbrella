package com.example.tomorrowweather.model.response

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("cod")
    val statusCode: String?,
    @SerializedName("message")
    val message: Int?,
    @SerializedName("cnt")
    val timestampsCount: Int?,
    @SerializedName("list")
    val timeStamps: List<TimeStamp>?,
    @SerializedName("city")
    val city: City?
)
