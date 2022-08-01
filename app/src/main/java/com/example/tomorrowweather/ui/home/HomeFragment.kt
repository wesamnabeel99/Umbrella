package com.example.tomorrowweather.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.tomorrowweather.databinding.FragmentHomeBinding
import com.example.tomorrowweather.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding = FragmentHomeBinding::inflate
}