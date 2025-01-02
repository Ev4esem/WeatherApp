package com.example.favourite_screen.domain.usecases

import com.example.favourite_screen.domain.repositories.FavouriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveFavouriteStateUseCase @Inject constructor(
    private val repository: FavouriteRepository
) {
    operator fun invoke(cityId: Int): Flow<Boolean> = repository.observeIsFavourite(cityId)
}