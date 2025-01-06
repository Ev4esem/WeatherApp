package com.example.details_screen.domain.entities

data class Forecast(
    val currentWeather: Weather,
    val upcoming: List<Weather>
)
