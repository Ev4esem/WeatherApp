package com.example.weatherapp.presentation.ui.screens

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.example.core.models.City
import com.example.details_screen.presentation.DetailsComponentImpl
import com.example.favourite_screen.presentation.FavouriteComponentImpl
import com.example.search_screen.presentation.OpenReason
import com.example.search_screen.presentation.SearchComponentImpl
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.parcelize.Parcelize

class RootComponentImpl @AssistedInject constructor(
    private val detailsComponentFactory: DetailsComponentImpl.Factory,
    private val favouriteComponentFactory:  FavouriteComponentImpl.Factory,
    private val searchComponentFactory: SearchComponentImpl.Factory,
    @Assisted("componentContext") componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = Config.Favourite,
        handleBackButton = true,
        childFactory = ::child
    )

    private fun child(
        config: Config,
        componentContext: ComponentContext,
    ): RootComponent.Child {
        return when(config) {
            is Config.Details -> {
                val component = detailsComponentFactory(
                    city = config.city,
                    onBackClicked = {
                        navigation.pop()
                    },
                    componentContext = componentContext,
                )
                RootComponent.Child.Details(component)
            }
            is Config.Favourite -> {
                val component = favouriteComponentFactory(
                    onCityItemClicked = { city ->
                        navigation.push(Config.Details(city))
                    },
                    onAddFavouriteClicked = {
                        navigation.push(Config.Search(OpenReason.AddToFavourite))
                    },
                    onSearchClicked = {
                        navigation.push(Config.Search(OpenReason.RegularSearch))
                    },
                    componentContext = componentContext,
                )
                RootComponent.Child.Favourite(component)
            }
            is Config.Search -> {
                val component = searchComponentFactory(
                    onBackClicked = {
                        navigation.pop()
                    },
                    openReason = config.openReason,
                    onClickFavourite = {
                        navigation.pop()
                    },
                    onClickForecast = { city ->
                        navigation.push(Config.Details(city))
                    },
                    componentContext = componentContext,
                )
                RootComponent.Child.Search(component)
            }
        }
    }

    sealed interface Config: Parcelable {

        @Parcelize
        data object Favourite: Config

        @Parcelize
        data class Search(val openReason: OpenReason): Config

        @Parcelize
        data class Details(val city: City): Config

    }

    @AssistedFactory
    interface Factory: RootComponent.Factory {
        override fun invoke(
            @Assisted("componentContext") componentContext: ComponentContext
        ): RootComponentImpl
    }

}