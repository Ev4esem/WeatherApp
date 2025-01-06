package com.example.weatherapp.presentation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.example.details_screen.presentation.DetailsComponent
import com.example.favourite_screen.presentation.FavouriteComponent
import com.example.search_screen.presentation.SearchComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {
        data class Favourite(val component: FavouriteComponent): Child
        data class Search(val component: SearchComponent): Child
        data class Details(val component: DetailsComponent): Child
    }

}