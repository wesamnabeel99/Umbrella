package com.example.tomorrowweather.model.repositories

import com.example.tomorrowweather.model.network.Client
import com.example.tomorrowweather.model.network.WeatherService
import com.example.tomorrowweather.model.response.WeatherResponse

class WeatherRepositoryImpl : WeatherRepository, WeatherService {
    override fun getWeatherData(): WeatherResponse {
        return Client.weatherResponse
    }

    override fun requestWeatherData(
        onSuccess: (isSuccess: Boolean) -> Unit,
    ) {
        Client.requestWeatherData(onSuccess)
    }
}