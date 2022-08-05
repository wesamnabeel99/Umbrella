package com.example.tomorrowweather.model.response

import com.google.gson.annotations.SerializedName

data class WeatherState(
    @SerializedName("id")
    val weatherConditionId: Int?,
    @SerializedName("main")
    val weatherState: String?,
    @SerializedName("description")
    val weatherStateDescription: String?,
    @SerializedName("icon")
    val weatherStateIcon: String?,
)
