package com.example.details_feature.domain.entities

data class Forecast(
    val currentWeather: Weather,
    val upcoming: List<Weather>
)
