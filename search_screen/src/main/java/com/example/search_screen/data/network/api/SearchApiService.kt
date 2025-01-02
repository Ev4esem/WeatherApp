package com.example.search_screen.data.network.api

import com.example.search_screen.data.network.dto.CityDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {

    @GET("search.json")
    suspend fun searchCity(
        @Query("q") query: String,
    ): List<CityDto>

}