package com.example.tomorrowweather.model.response

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val countryName: String?,
    @SerializedName("coord")
    val coordinates: Coordinates?,
    @SerializedName("country")
    val shortCountryName: String?,
    @SerializedName("population")
    val population: Long?,
    @SerializedName("timezone")
    val timezone: Int?,
    @SerializedName("sunrise")
    val sunrise: Int?,
    @SerializedName("sunset")
    val sunset: Int?,

    )
