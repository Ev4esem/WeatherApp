package com.example.favourite_feature.domain.usecases

import com.example.favourite_feature.domain.entities.City
import com.example.favourite_feature.domain.repositories.FavouriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavouriteCitiesUseCase @Inject constructor(
    private val repository: FavouriteRepository
) {
    operator fun invoke(): Flow<City> = repository.favouriteCities
}