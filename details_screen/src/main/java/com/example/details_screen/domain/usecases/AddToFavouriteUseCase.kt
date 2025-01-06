package com.example.details_screen.domain.usecases

import com.example.core.models.City
import com.example.details_screen.domain.repositories.DetailsRepository
import javax.inject.Inject

class AddToFavouriteUseCase @Inject constructor(
    private val repository: DetailsRepository
) {
    suspend operator fun invoke(city: City) = repository.addToFavourite(city)
}