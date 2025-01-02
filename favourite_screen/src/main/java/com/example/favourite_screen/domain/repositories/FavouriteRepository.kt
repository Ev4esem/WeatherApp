package com.example.favourite_screen.domain.repositories

import com.example.favourite_screen.domain.entities.City
import com.example.favourite_screen.domain.entities.Weather
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {

    val favouriteCities: Flow<List<City>>

    suspend fun getWeather(cityId: Int): Weather

    fun observeIsFavourite(cityId: Int): Flow<Boolean>

    suspend fun addToFavourite(city: City)

    suspend fun removeFromFavourite(cityId: Int)

}