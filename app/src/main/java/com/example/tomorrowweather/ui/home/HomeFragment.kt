package com.example.tomorrowweather.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.tomorrowweather.R
import com.example.tomorrowweather.databinding.FragmentHomeBinding
import com.example.tomorrowweather.model.repositories.WeatherRepositoryImpl
import com.example.tomorrowweather.model.response.TimeStamp
import com.example.tomorrowweather.ui.base.BaseFragment
import com.example.tomorrowweather.utils.Constants

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding = FragmentHomeBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = WeatherRepositoryImpl()
        binding.errorContainer.visibility = View.GONE
        repository.requestWeatherData() { isSuccess ->
            if (isSuccess) {
                val timeStamp = repository.getRecentTimeStamp()
                activity?.runOnUiThread {
                    binding.loadingContainer.visibility = View.GONE
                    binding.countryName.text = repository.getCountryName()
                    bindDataIntoUi(timeStamp)
                    setBackgroundColorBasedOnTime(timeStamp)
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


    private fun bindDataIntoUi(timeStamp: TimeStamp?) {
        binding.apply {
            time.text = timeStamp?.dateText
            temperatureTextView.text = timeStamp?.weatherInformation?.temperature.toString() + "°"
            weatherConditionTextView.text = timeStamp?.weatherState?.get(Constants.FIRST_WEATHER_STATE)?.weatherState
            humidityValueTextView.text = timeStamp?.weatherInformation?.humidity.toString() + "%"
            windValueTextView.text = timeStamp?.wind?.speed.toString() +" km/h"
            cloudsValueTextView.text = timeStamp?.clouds?.cloudsPercent?.toString() + "%"
            rainValueTextView.text = timeStamp?.probability?.toString() + "%"
        }
    }

    private fun setBackgroundColorBasedOnTime(timeStamp: TimeStamp?) {
        val dayState = timeStamp?.timeOfTheDay?.timeOfDay
        if (dayState == "n") {
            binding.successContainer.background = ContextCompat.getDrawable(requireActivity(),R.drawable.night_time_background)
        } else {
            binding.successContainer.background = ContextCompat.getDrawable(requireActivity(),R.drawable.day_time_background)
        }
    }
}