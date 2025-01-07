package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.di.AppComponent
import com.example.weatherapp.di.DaggerAppComponent

class WeatherApp: Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}
