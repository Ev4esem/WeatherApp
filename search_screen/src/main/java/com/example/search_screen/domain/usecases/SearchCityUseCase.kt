package com.example.search_screen.domain.usecases

import com.example.core.models.City
import com.example.search_screen.domain.repositories.SearchRepository
import javax.inject.Inject

class SearchCityUseCase @Inject constructor (
    private val repository: SearchRepository
) {
   suspend operator fun invoke(query: String): List<City> = repository.search(query)
}