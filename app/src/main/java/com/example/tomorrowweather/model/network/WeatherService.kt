package com.example.tomorrowweather.model.network

interface WeatherService {
    fun requestWeatherData(
        onSuccess: (isSuccess: Boolean) -> Unit,
    )
}