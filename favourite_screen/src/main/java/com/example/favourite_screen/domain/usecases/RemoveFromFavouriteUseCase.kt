package com.example.favourite_screen.domain.usecases

import com.example.favourite_screen.domain.repositories.FavouriteRepository
import javax.inject.Inject

class RemoveFromFavouriteUseCase @Inject constructor(
    private val repository: FavouriteRepository
) {
    suspend operator fun invoke(cityId: Int) = repository.removeFromFavourite(cityId)
}