package com.example.tomorrowweather.ui

import android.view.LayoutInflater
import com.example.tomorrowweather.databinding.ActivityMainBinding
import com.example.tomorrowweather.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate
}