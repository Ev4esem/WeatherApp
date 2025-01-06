package com.example.details_screen.domain.repositories

import com.example.core.models.City
import com.example.details_screen.domain.entities.Forecast
import com.example.details_screen.domain.entities.Weather
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {

    suspend fun getWeather(cityId: Int): Weather

    suspend fun getForecast(cityId: Int): Forecast

    suspend fun addToFavourite(city: City)

    suspend fun removeFromFavourite(cityId: Int)

    fun observeIsFavourite(cityId: Int): Flow<Boolean>

}