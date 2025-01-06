package com.example.search_screen.data

import com.example.core.db.FavouriteCitiesDao
import com.example.core.mappers.toCityEntity
import com.example.core.models.City
import com.example.search_screen.data.network.api.SearchApiService
import com.example.search_screen.data.network.mapper.toCities
import com.example.search_screen.domain.repositories.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor (
    private val favouriteCitiesDao: FavouriteCitiesDao,
    private val apiService: SearchApiService
) : SearchRepository {

    override suspend fun search(query: String): List<City> {
        return apiService.searchCity(query).toCities()
    }

    override suspend fun addToFavourite(city: City) {
        favouriteCitiesDao.addToFavourite(city.toCityEntity())
    }

}