package com.example.tomorrowweather.model.response

import com.google.gson.annotations.SerializedName

data class Cloud(
    @SerializedName("all")
    val cloudsPercent: Int?,
)
