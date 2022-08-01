package com.example.tomorrowweather.model.response

import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("speed")
    val speed: Double?,
    @SerializedName("deg")
    val degree: Int?,
    @SerializedName("gust")
    val gust: Double?,
)
