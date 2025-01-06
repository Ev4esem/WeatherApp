package com.example.favourite_screen.domain.usecases

import com.example.core.models.City
import com.example.favourite_screen.domain.repositories.FavouriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavouriteCitiesUseCase @Inject constructor(
    private val repository: FavouriteRepository
) {
    operator fun invoke(): Flow<List<City>> = repository.favouriteCities
}