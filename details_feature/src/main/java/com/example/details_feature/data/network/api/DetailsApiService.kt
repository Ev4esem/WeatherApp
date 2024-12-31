package com.example.details_feature.data.network.api

import com.example.details_feature.data.network.dto.WeatherCurrentDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DetailsApiService {

    @GET("current.json")
    suspend fun loadCurrentWeather(
        @Query("q") query: String,
    ): WeatherCurrentDto

    @GET("forecast.json")
    suspend fun loadForecast(
        @Query("q") query: String,
        @Query("days") daysCount: Int = 4,
    ): WeatherCurrentDto

}