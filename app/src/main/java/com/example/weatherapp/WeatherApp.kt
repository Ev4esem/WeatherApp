package com.example.weatherapp

import android.app.Application
import com.example.core.di.DatabaseStore
import com.example.details_screen.di.DetailsDependenciesStore
import com.example.favourite_screen.di.FavouriteDependenciesStore
import com.example.search_screen.di.SearchDependenciesStore
import com.example.weatherapp.di.AppComponent
import com.example.weatherapp.di.DaggerAppComponent

class WeatherApp: Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        SearchDependenciesStore.deps = appComponent
        FavouriteDependenciesStore.deps = appComponent
        DetailsDependenciesStore.deps = appComponent
        DatabaseStore.deps = appComponent
    }

}
