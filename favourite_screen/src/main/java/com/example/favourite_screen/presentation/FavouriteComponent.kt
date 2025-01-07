package com.example.favourite_screen.presentation

import com.arkivanov.decompose.ComponentContext
import com.example.core.models.City
import kotlinx.coroutines.flow.StateFlow

interface FavouriteComponent {

    val model: StateFlow<FavouriteStore.State>

    fun onClickSearch()

    fun onClickAddFavourite()

    fun onCityItemClick(city: City)

    fun interface Factory {
        operator fun invoke(
            onCityItemClicked: (City) -> Unit,
            onAddFavouriteClicked: () -> Unit,
            onSearchClicked: () -> Unit,
            componentContext: ComponentContext,
        ): FavouriteComponent
    }

}