package com.example.tomorrowweather

import android.os.Bundle
import android.view.LayoutInflater
import com.example.tomorrowweather.databinding.ActivityMainBinding
import com.example.tomorrowweather.ui.base.BaseActivity
import com.example.tomorrowweather.ui.home.HomeFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding = ActivityMainBinding::inflate
}