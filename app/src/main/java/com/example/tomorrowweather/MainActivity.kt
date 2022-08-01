package com.example.tomorrowweather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.example.tomorrowweather.databinding.ActivityMainBinding
import com.example.tomorrowweather.model.repositories.WeatherRepositoryImpl
import com.example.tomorrowweather.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate
    val repository = WeatherRepositoryImpl()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository.requestWeatherData() { isSuccess ->
            if (isSuccess) {
                Log.i("MAIN_ACTIVITY", "done!")
                Log.i("MAIN_ACTIVITY",repository.getWeatherData().toString())
            } else {
                Log.i("MAIN_ACTIVITY", "failed")
            }
        }
    }
}