package com.example.favourite_screen.domain.repositories

import com.example.core.models.City
import com.example.favourite_screen.domain.entities.Weather
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {

    val favouriteCities: Flow<List<City>>

    suspend fun getWeather(cityId: Int): Weather

}