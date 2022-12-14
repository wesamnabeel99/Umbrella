package com.example.tomorrowweather.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tomorrowweather.R
import com.example.tomorrowweather.databinding.ItemWeatherBinding
import com.example.tomorrowweather.model.response.TimeStamp
import com.example.tomorrowweather.utils.Constants
import com.example.tomorrowweather.utils.loadImageUrl
import com.example.tomorrowweather.utils.setBackgroundColorBasedOnTime
import com.example.tomorrowweather.utils.toDate

class WeatherAdapter (private var timeStamps: List<TimeStamp>) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val currentTimeStamp = timeStamps[position]
        val currentWeatherState = currentTimeStamp.weatherState?.get(Constants.FIRST_WEATHER_STATE)
        val currentTemperature = currentTimeStamp.weatherInformation?.temperature.toString()
        val currentDate =  currentTimeStamp.time!!
        val currentWeatherIconId = currentWeatherState?.weatherStateIcon
        val currentTimeOfTheDay = currentTimeStamp.timeOfTheDay?.timeOfDay.toString()
        val currentWeatherIconUrl = "https://openweathermap.org/img/wn/${currentWeatherIconId}@2x.png"

        holder.binding.apply {
            weatherIconImageView.loadImageUrl(currentWeatherIconUrl)
            weatherStateTextView.text = currentWeatherState?.weatherState
            temperatureTextView.text = currentTemperature + "°"
            timeTextView.text = currentDate.toDate()

            itemContainer.setBackgroundColorBasedOnTime(
                context = this.root.context,
                timeOfTheDay = currentTimeOfTheDay,
                nightDrawableId = R.color.background_night_center_color,
                dayDrawableId = R.color.background_day_center_color
            )
        }
    }



    override fun getItemCount(): Int = timeStamps.size


    class WeatherViewHolder(weatherItem: View) : RecyclerView.ViewHolder(weatherItem) {
        val binding = ItemWeatherBinding.bind(weatherItem)
    }

}