package com.example.tomorrowweather.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

fun ImageView.loadImageUrl(url:String){
    Glide.with(this.context).load(url).into(this)
}

fun View.setBackgroundColorBasedOnTime(timeOfTheDay: String, context: Context, nightDrawableId : Int, dayDrawableId: Int) {
    if (timeOfTheDay == "n") {
        this.background = ContextCompat.getDrawable(context, nightDrawableId)
    } else {
        this.background = ContextCompat.getDrawable(context, dayDrawableId)
    }
}