package com.example.favourite_screen.data.network.api

import com.example.favourite_screen.data.network.dto.WeatherCurrentDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FavouriteApiService {

    @GET("current.json")
    suspend fun loadCurrentWeather(
        @Query("q") query: String,
    ): WeatherCurrentDto

}