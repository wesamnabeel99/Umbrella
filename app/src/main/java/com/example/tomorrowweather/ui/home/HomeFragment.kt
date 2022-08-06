package com.example.tomorrowweather.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.tomorrowweather.R
import com.example.tomorrowweather.databinding.FragmentHomeBinding
import com.example.tomorrowweather.model.repositories.WeatherRepositoryImpl
import com.example.tomorrowweather.model.response.TimeStamp
import com.example.tomorrowweather.ui.adapters.WeatherAdapter
import com.example.tomorrowweather.ui.base.BaseFragment
import com.example.tomorrowweather.utils.*
import org.eazegraph.lib.models.ValueLinePoint
import org.eazegraph.lib.models.ValueLineSeries


class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = WeatherRepositoryImpl()

        binding.errorContainer.hide()


        repository.requestWeatherData() { isSuccess ->
            if (isSuccess) {
                val timeStamps = repository.getRecentTimeStamp()
                val currentTimeStamp = timeStamps?.get(Constants.RECENT_TIMESTAMP)
                activity?.runOnUiThread {
                    Log.i("MAIN", "done")
                    binding.loadingContainer.hide()
                    binding.countryName.text = repository.getCountryName()

                    bindDataIntoUi(currentTimeStamp)

                    setAdapter(timeStamps?.filter { it != currentTimeStamp })

                    binding.successContainer.setBackgroundColorBasedOnTime(
                        timeOfTheDay = currentTimeStamp?.timeOfTheDay?.timeOfDay.toString(),
                        context = requireActivity(),
                        nightDrawableId = R.drawable.night_time_background,
                        dayDrawableId = R.drawable.day_time_background
                    )

                    setTemperatureLineChart(
                        chart = binding.temperatureLineChart,
                        timeStamps = timeStamps!!,
                        seriesColor = ContextCompat.getColor(requireContext(), R.color.temperature)
                    )
                }
            } else {
                activity?.runOnUiThread {
                    binding.successContainer.hide()
                    binding.loadingContainer.hide()
                    binding.errorContainer.show()

                    Toast.makeText(
                        activity,
                        "Please check your internet connection & try again",
                        Toast.LENGTH_LONG
                    ).show()

                }
            }
        }

    }

    private fun setTemperatureLineChart(
        chart: org.eazegraph.lib.charts.ValueLineChart,
        timeStamps: List<TimeStamp>,
        seriesColor: Int,
    ) {
        val series = ValueLineSeries()
        series.color = seriesColor

        timeStamps.forEach {
            series.addPoint(
                ValueLinePoint(
                    it.time?.toDate()!!.split(",")[0],
                    it.weatherInformation?.temperature!!.toFloat()
                )
            )
        }
        chart.addSeries(series)
    }

    private fun setAdapter(timeStamps: List<TimeStamp>?) {
        val adapter = timeStamps?.toList()?.let { timeStamps -> WeatherAdapter(timeStamps) }
        activity?.runOnUiThread {
            binding.recyclerView.adapter = adapter
        }
    }


    private fun bindDataIntoUi(timeStamp: TimeStamp?) {
        val weatherInformation = timeStamp?.weatherInformation
        val weatherState = timeStamp?.weatherState?.get(Constants.FIRST_WEATHER_STATE)
        val windSpeed = timeStamp?.wind?.speed.toString() + " km/h"
        val cloudsPercentage = timeStamp?.clouds?.cloudsPercent?.toString() + "%"
        val rainProbability = timeStamp?.probability?.toString() + "%"
        val weatherIconId = weatherState?.weatherStateIcon
        val weatherStateIconUrl = "https://openweathermap.org/img/wn/${weatherIconId}@2x.png"

        binding.apply {
            time.text = timeStamp?.time!!.toDate()
            temperatureTextView.text = weatherInformation?.temperature.toString() + "°"
            humidityValueTextView.text = weatherInformation?.humidity.toString() + "%"
            weatherStateTextView.text = weatherState?.weatherState
            windValueTextView.text = windSpeed
            cloudsValueTextView.text = cloudsPercentage
            rainValueTextView.text = rainProbability
            weatherSymbolAnimation.loadImageUrl(weatherStateIconUrl)
        }
    }


}