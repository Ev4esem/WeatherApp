package com.example.favourite_screen.domain.usecases

import com.example.favourite_screen.domain.entities.City
import com.example.favourite_screen.domain.repositories.FavouriteRepository
import javax.inject.Inject

class AddToFavouriteUseCase @Inject constructor(
    private val repository: FavouriteRepository
) {
    suspend operator fun invoke(city: City) = repository.addToFavourite(city)
}