package com.example.search_screen.presentation

import com.arkivanov.decompose.ComponentContext
import com.example.core.models.City
import kotlinx.coroutines.flow.StateFlow

interface SearchComponent {

    val model: StateFlow<SearchStore.State>

    fun changeSearchQuery(query: String)

    fun onClickBack()

    fun onClickSearch()

    fun onClickCity(city: City)

    fun interface Factory {
        operator fun invoke(
            openReason: OpenReason,
            onBackClicked: () -> Unit,
            onClickForecast: (City) -> Unit,
            onClickFavourite: () -> Unit,
            componentContext: ComponentContext,
        ): SearchComponent
    }

}