package com.example.favourite_feature.domain.usecases

import com.example.favourite_feature.domain.repositories.FavouriteRepository
import javax.inject.Inject

class RemoveFromFavouriteUseCase @Inject constructor(
    private val repository: FavouriteRepository
) {
    suspend operator fun invoke(cityId: Int) = repository.removeFromFavourite(cityId)
}