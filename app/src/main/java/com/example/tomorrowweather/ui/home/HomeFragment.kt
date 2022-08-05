package com.example.tomorrowweather.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tomorrowweather.R
import com.example.tomorrowweather.databinding.FragmentHomeBinding
import com.example.tomorrowweather.model.repositories.WeatherRepositoryImpl
import com.example.tomorrowweather.model.response.TimeStamp
import com.example.tomorrowweather.ui.adapters.WeatherAdapter
import com.example.tomorrowweather.ui.base.BaseFragment
import com.example.tomorrowweather.utils.Constants
import com.example.tomorrowweather.utils.loadImageUrl
import com.example.tomorrowweather.utils.setBackgroundColorBasedOnTime

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding = FragmentHomeBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = WeatherRepositoryImpl()
        binding.errorContainer.visibility = View.GONE
        repository.requestWeatherData() { isSuccess ->
            if (isSuccess) {
                val timeStamps = repository.getRecentTimeStamp()
                val currentTimeStamp = timeStamps?.get(Constants.RECENT_TIMESTAMP)
                activity?.runOnUiThread {
                    binding.loadingContainer.visibility = View.GONE
                    binding.countryName.text = repository.getCountryName()
                    bindDataIntoUi(currentTimeStamp)
                    setAdapter(timeStamps?.subList(Constants.RECENT_TIMESTAMP+1, timeStamps.size-1))
                    binding.successContainer.setBackgroundColorBasedOnTime(
                        timeOfTheDay = currentTimeStamp?.timeOfTheDay?.timeOfDay.toString(),
                        context = requireActivity(),
                        nightDrawableId = R.drawable.night_time_background,
                        dayDrawableId = R.drawable.day_time_background
                    )
                }
            } else {
                activity?.runOnUiThread {
                    binding.successContainer.visibility = View.GONE
                    binding.loadingContainer.visibility = View.GONE
                    binding.errorContainer.visibility = View.VISIBLE
                    Toast.makeText(activity,"Please check your internet connection & try again",Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setAdapter(timeStamps: List<TimeStamp>?) {
        val adapter = WeatherAdapter(timeStamps!!.toList())
        activity?.runOnUiThread{
            binding.recyclerView.adapter = adapter
        }
    }


    private fun bindDataIntoUi(timeStamp: TimeStamp?) {
        binding.apply {
            time.text = timeStamp?.dateText
            temperatureTextView.text = timeStamp?.weatherInformation?.temperature.toString() + "°"
            weatherConditionTextView.text = timeStamp?.weatherState?.get(Constants.FIRST_WEATHER_STATE)?.weatherState
            humidityValueTextView.text = timeStamp?.weatherInformation?.humidity.toString() + "%"
            windValueTextView.text = timeStamp?.wind?.speed.toString() +" km/h"
            cloudsValueTextView.text = timeStamp?.clouds?.cloudsPercent?.toString() + "%"
            rainValueTextView.text = timeStamp?.probability?.toString() + "%"
            val weatherIconId = timeStamp?.weatherState?.get(Constants.FIRST_WEATHER_STATE)?.weatherStateIcon
            weatherSymbolAnimation.loadImageUrl("https://openweathermap.org/img/wn/${weatherIconId}@2x.png")
        }
    }


}