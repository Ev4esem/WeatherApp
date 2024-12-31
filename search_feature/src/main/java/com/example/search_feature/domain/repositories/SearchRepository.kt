package com.example.search_feature.domain.repositories

import com.example.search_feature.domain.entities.City

interface SearchRepository {

    suspend fun search(query: String): List<City>

}