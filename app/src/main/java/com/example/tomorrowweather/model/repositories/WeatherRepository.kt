package com.example.tomorrowweather.model.repositories

import com.example.tomorrowweather.model.response.WeatherResponse

interface WeatherRepository {
    fun getWeatherData(): WeatherResponse
}