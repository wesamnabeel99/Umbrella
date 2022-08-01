package com.example.tomorrowweather.utils

object Constants {
    const val SCHEME = "https"
    const val BASE_URL = "api.openweathermap.org"
    const val PATH_SEGMENTS = "data/2.5/forecast"

    object QueryKeys {
        const val APP_ID = "appid"
        const val LAT = "lat"
        const val LON = "lon"
        const val UNITS = "units"
    }

    object QueryValues {
        const val APP_ID = "5e605742def44be8bc8a29d7fa8d6087"
        const val IRAQ_LAT = "33"
        const val IRAQ_LON = "44"
        const val UNITS = "metric"
    }
}