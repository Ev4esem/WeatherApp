package com.example.search_screen.domain.repositories

import com.example.core.models.City


interface SearchRepository {

    suspend fun search(query: String): List<City>

    suspend fun addToFavourite(city: City)

}