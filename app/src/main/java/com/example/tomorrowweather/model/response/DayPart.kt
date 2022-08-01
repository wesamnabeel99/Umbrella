package com.example.tomorrowweather.model.response

import com.google.gson.annotations.SerializedName

data class DayPart(
    @SerializedName("pod")
    val timeOfDay: String?
)
