package com.example.search_screen.domain.usecases

import com.example.core.models.City
import com.example.search_screen.domain.repositories.SearchRepository
import javax.inject.Inject

class AddToFavouriteUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(city: City) = repository.addToFavourite(city)
}