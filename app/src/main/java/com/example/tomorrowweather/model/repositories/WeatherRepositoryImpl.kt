package com.example.tomorrowweather.model.repositories

import com.example.tomorrowweather.model.network.Client
import com.example.tomorrowweather.model.network.WeatherService
import com.example.tomorrowweather.model.response.TimeStamp

class WeatherRepositoryImpl : WeatherRepository, WeatherService {
    override fun getRecentTimeStamp(): List<TimeStamp>? {
        return Client.weatherResponse.timeStamps
    }

    override fun getCountryName() = Client.weatherResponse.city?.name

    override fun requestWeatherData(
        onSuccess: (isSuccess: Boolean) -> Unit,
    ) {
        Client.requestWeatherData(onSuccess)
    }
}