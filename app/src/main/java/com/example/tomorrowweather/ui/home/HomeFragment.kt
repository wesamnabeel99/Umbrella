package com.example.tomorrowweather.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tomorrowweather.databinding.FragmentHomeBinding
import com.example.tomorrowweather.model.repositories.WeatherRepositoryImpl
import com.example.tomorrowweather.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding = FragmentHomeBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = WeatherRepositoryImpl()
        repository.requestWeatherData() { isSuccess ->
            if (isSuccess) {
                Log.i("HOME_FRAGMENT", "done!")
                Log.i("HOME_FRAGMENT", repository.getWeatherData().timeStamps?.get(0).toString())
            } else {
                Log.i("HOME_FRAGMENT", "failed")
            }
        }
    }
}