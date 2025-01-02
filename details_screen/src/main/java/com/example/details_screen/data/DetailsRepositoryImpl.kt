package com.example.details_screen.data

import com.example.details_feature.data.mapper.toForecast
import com.example.details_feature.data.mapper.toWeatherCurrent
import com.example.details_screen.data.network.api.DetailsApiService
import com.example.details_feature.domain.entities.Forecast
import com.example.details_feature.domain.entities.Weather
import com.example.details_screen.domain.repositories.DetailsRepository

class DetailsRepositoryImpl(
    private val detailsApiService: DetailsApiService
) : DetailsRepository {
    override suspend fun getWeather(cityId: Int): Weather {
        return detailsApiService.loadCurrentWeather("$PREFIX_CITY_ID$cityId").toWeatherCurrent()
    }

    override suspend fun getForecast(cityId: Int): Forecast {
        return detailsApiService.loadForecast("$PREFIX_CITY_ID$cityId").toForecast()
    }

    private companion object {
        private const val PREFIX_CITY_ID = "id:"
    }

}