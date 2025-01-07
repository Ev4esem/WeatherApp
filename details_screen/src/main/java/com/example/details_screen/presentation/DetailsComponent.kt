package com.example.details_screen.presentation

import com.arkivanov.decompose.ComponentContext
import com.example.core.models.City
import kotlinx.coroutines.flow.StateFlow

interface DetailsComponent {

    val model: StateFlow<DetailsStore.State>

    fun onClickBack()

    fun onClickChangeFavouriteStatus()

    fun interface Factory {
        operator fun invoke(
            city: City,
            onBackClicked: () -> Unit,
            componentContext: ComponentContext
        ): DetailsComponent
    }

}