package com.example.search_screen.data

import com.example.search_screen.data.network.api.SearchApiService
import com.example.search_screen.data.network.mapper.toCities
import com.example.search_screen.domain.entities.City
import com.example.search_screen.domain.repositories.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor (
    private val apiService: SearchApiService
) : SearchRepository {
    override suspend fun search(query: String): List<City> {
        return apiService.searchCity(query).toCities()
    }
}