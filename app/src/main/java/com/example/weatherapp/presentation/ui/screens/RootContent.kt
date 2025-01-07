package com.example.weatherapp.presentation.ui.screens

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.example.details_screen.presentation.DetailsContent
import com.example.favourite_screen.presentation.FavouriteContent
import com.example.search_screen.presentation.SearchContent
import com.example.weatherapp.presentation.ui.theme.WeatherAppTheme

@Composable
fun RootContent(component: RootComponent) {
    WeatherAppTheme {
        Children(
            stack = component.stack
        ) {
            when(val instance = it.instance) {
                is RootComponent.Child.Details -> {
                    DetailsContent(
                        component = instance.component
                    )
                }
                is RootComponent.Child.Favourite -> {
                    FavouriteContent(
                        component = instance.component
                    )
                }
                is RootComponent.Child.Search -> {
                    SearchContent(
                        component = instance.component
                    )
                }
            }
        }
    }
}