package com.example.details_screen.data

import com.example.core.db.FavouriteCitiesDao
import com.example.core.mappers.toCityEntity
import com.example.core.models.City
import com.example.details_screen.data.mapper.toForecast
import com.example.details_screen.data.mapper.toWeatherCurrent
import com.example.details_screen.domain.entities.Forecast
import com.example.details_screen.domain.entities.Weather
import com.example.details_screen.data.network.api.DetailsApiService
import com.example.details_screen.domain.repositories.DetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val favouriteCitiesDao: FavouriteCitiesDao,
    private val detailsApiService: DetailsApiService,
) : DetailsRepository {

    override suspend fun getWeather(cityId: Int): Weather {
        return detailsApiService.loadCurrentWeather("$PREFIX_CITY_ID$cityId").toWeatherCurrent()
    }

    override suspend fun getForecast(cityId: Int): Forecast {
        return detailsApiService.loadForecast("$PREFIX_CITY_ID$cityId").toForecast()
    }

    override suspend fun addToFavourite(city: City) {
        favouriteCitiesDao.addToFavourite(city.toCityEntity())
    }

    override suspend fun removeFromFavourite(cityId: Int) {
        favouriteCitiesDao.removeFromFavourite(cityId)
    }

    override fun observeIsFavourite(cityId: Int): Flow<Boolean> {
        return favouriteCitiesDao.observeIsFavourite(cityId)
    }

    private companion object {
        private const val PREFIX_CITY_ID = "id:"
    }

}