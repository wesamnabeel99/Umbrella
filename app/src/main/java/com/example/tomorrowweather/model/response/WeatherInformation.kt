package com.example.tomorrowweather.model.response

import com.google.gson.annotations.SerializedName

data class WeatherInformation(
    @SerializedName("temp")
    val temperature: Double?,
    @SerializedName("feels_like")
    val feelsLike: Double?,
    @SerializedName("temp_min")
    val minimumTemperature: Double?,
    @SerializedName("temp_max")
    val maximumTemperature: Double?,
    @SerializedName("pressure")
    val pressure: Int?,
    @SerializedName("sea_level")
    val seaLevel: Int?,
    @SerializedName("grnd_level")
    val groundLevel: Int?,
    @SerializedName("humidity")
    val humidity: Int?,
    @SerializedName("temp_kf")
    val tempKF: Double?,
)
