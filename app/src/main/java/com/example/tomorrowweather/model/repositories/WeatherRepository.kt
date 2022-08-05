package com.example.tomorrowweather.model.repositories

import com.example.tomorrowweather.model.response.TimeStamp
import com.example.tomorrowweather.model.response.WeatherResponse

interface WeatherRepository {
    fun getRecentTimeStamp(): List<TimeStamp>?
    fun getCountryName() : String?
}