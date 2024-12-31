package com.example.details_feature.domain.usecases

import com.example.details_feature.domain.entities.Weather
import com.example.details_feature.domain.repositories.DetailsRepository
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor (
    private val repository: DetailsRepository
) {
    suspend operator fun invoke(cityId: Int): Weather = repository.getWeather(cityId)
}