package com.example.favourite_screen.data

import com.example.core.db.FavouriteCitiesDao
import com.example.core.mappers.toCities
import com.example.core.mappers.toCityEntity
import com.example.core.models.City
import com.example.favourite_screen.data.mapper.toWeatherCurrent
import com.example.favourite_screen.data.network.api.FavouriteApiService
import com.example.favourite_screen.domain.entities.Weather
import com.example.favourite_screen.domain.repositories.FavouriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor (
    private val favouriteCitiesDao: FavouriteCitiesDao,
    private val favouriteApiService: FavouriteApiService,
) : FavouriteRepository {
    override val favouriteCities: Flow<List<City>> = favouriteCitiesDao.getFavouriteCities()
        .map { it.toCities() }

    override suspend fun getWeather(cityId: Int): Weather {
        return favouriteApiService.loadCurrentWeather("$PREFIX_CITY_ID$cityId").toWeatherCurrent()
    }

    private companion object {
        private const val PREFIX_CITY_ID = "id:"
    }

}