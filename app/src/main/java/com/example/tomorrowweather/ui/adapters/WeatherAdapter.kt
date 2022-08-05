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

class WeatherAdapter (private var timeStamps: List<TimeStamp>) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val currentTimeStamp = timeStamps.get(position)
        val currentWeatherState = currentTimeStamp.weatherState
        val currentWeatherIconId = currentWeatherState?.get(Constants.FIRST_WEATHER_STATE)?.weatherStateIcon
        val currentWeatherIconUrl = "https://openweathermap.org/img/wn/${currentWeatherIconId}@2x.png"
        holder.binding.apply {
            weatherIcon.loadImageUrl(currentWeatherIconUrl)
            itemWeatherState.text = currentWeatherState?.get(Constants.FIRST_WEATHER_STATE)?.weatherState
            temperature.text = currentTimeStamp.weatherInformation?.temperature.toString() + "°"
            itemTime.text = currentTimeStamp.dateText
            itemContainer.setBackgroundColorBasedOnTime(
                context = this.root.context,
                timeOfTheDay = currentTimeStamp.timeOfTheDay?.timeOfDay.toString(),
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