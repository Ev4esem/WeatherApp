package com.example.search_feature.data.network.api

import com.example.search_feature.data.network.dto.CityDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {

    @GET("search.json")
    suspend fun searchCity(
        @Query("q") query: String,
    ): List<CityDto>

}