package com.example.details_screen.domain.usecases

import com.example.details_screen.domain.repositories.DetailsRepository
import javax.inject.Inject

class RemoveFromFavouriteUseCase @Inject constructor(
    private val repository: DetailsRepository
) {
    suspend operator fun invoke(cityId: Int) = repository.removeFromFavourite(cityId)
}