package com.example.details_feature.domain.repositories

import com.example.details_feature.domain.entities.Forecast
import com.example.details_feature.domain.entities.Weather

interface DetailsRepository {

    suspend fun getWeather(cityId: Int): Weather

    suspend fun getForecast(cityId: Int): Forecast

}