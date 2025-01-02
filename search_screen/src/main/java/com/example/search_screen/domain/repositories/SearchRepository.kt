package com.example.search_screen.domain.repositories

import com.example.search_screen.domain.entities.City

interface SearchRepository {

    suspend fun search(query: String): List<City>

}