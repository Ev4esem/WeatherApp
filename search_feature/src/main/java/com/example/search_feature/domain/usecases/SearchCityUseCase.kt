package com.example.search_feature.domain.usecases

import com.example.search_feature.domain.entities.City
import com.example.search_feature.domain.repositories.SearchRepository
import javax.inject.Inject

class SearchCityUseCase @Inject constructor (
    private val repository: SearchRepository
) {
   suspend operator fun invoke(query: String): List<City> = repository.search(query)
}