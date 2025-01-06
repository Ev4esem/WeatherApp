package com.example.details_screen.domain.usecases

import com.example.details_screen.domain.repositories.DetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveFavouriteStateUseCase @Inject constructor(
    private val repository: DetailsRepository
) {
    operator fun invoke(cityId: Int): Flow<Boolean> = repository.observeIsFavourite(cityId)
}