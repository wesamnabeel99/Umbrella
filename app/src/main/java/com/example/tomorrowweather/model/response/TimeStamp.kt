package com.example.tomorrowweather.model.response

import com.google.gson.annotations.SerializedName

data class TimeStamp(
    @SerializedName("dt")
    val time: Long?,
    @SerializedName("main")
    val weatherInformation: WeatherInformation?,
    @SerializedName("weather")
    val weatherState: List<WeatherCondition>?,
    @SerializedName("clouds")
    val clouds: Cloud?,
    @SerializedName("wind")
    val wind: Wind?,
    @SerializedName("visibility")
    val visibility: Int?,
    @SerializedName("pop")
    val probability: Int?,
    @SerializedName("sys")
    val timeOfTheDay: DayPart?,
    @SerializedName("dt_txt")
    val dateText: String?
)