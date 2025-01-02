package com.example.favourite_screen.presentation

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.favourite_screen.domain.entities.City
import com.example.favourite_screen.domain.usecases.GetCurrentWeatherUseCase
import com.example.favourite_screen.domain.usecases.GetFavouriteCitiesUseCase
import com.example.favourite_screen.presentation.FavouriteStore.Intent
import com.example.favourite_screen.presentation.FavouriteStore.Label
import com.example.favourite_screen.presentation.FavouriteStore.State
import kotlinx.coroutines.launch
import javax.inject.Inject

internal interface FavouriteStore : Store<Intent, State, Label> {

    sealed interface Intent {

        data object ClickSearch: Intent

        data object ClickToFavourite: Intent

        data class SelectCity(
           val cityId: Int
        ): Intent

    }

    data class State(
        val cityItems: List<CityItem>
    ) {

        data class CityItem(
            val city: City,
            val weatherState: WeatherState,
        )

        sealed interface WeatherState {

            data object Loading: WeatherState

            data object Initial: WeatherState

            data object Error: WeatherState

            data class Loaded(
                val tempC: Float,
                val iconUrl: String,
            ): WeatherState

        }
    }

    sealed interface Label {

        data object ClickSearch: Label

        data object ClickToFavourite: Label

        data class SelectCity(
            val cityId: Int
        ): Label

    }
}

internal class FavouriteStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val getFavouriteCitiesUseCase: GetFavouriteCitiesUseCase,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
) {

    fun create(): FavouriteStore =
        object : FavouriteStore, Store<Intent, State, Label> by storeFactory.create(
            name = "FavouriteStore",
            initialState = State(
               cityItems = emptyList()
            ),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {

        data class FavoriteCitiesLoaded(val cities: List<City>): Action

    }

    private sealed interface Msg {

        data class FavoriteCitiesLoaded(val cities: List<City>): Msg

        data class WeatherLoaded(
            val cityId: Int,
            val tempC: Float,
            val conditionUrl: String
        ): Msg

        data class WeatherLoadingError(
            val cityId: Int
        ): Msg

        data class WeatherIsLoading(
            val cityId: Int
        ): Msg

    }

    private inner class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                getFavouriteCitiesUseCase().collect {
                    dispatch(Action.FavoriteCitiesLoaded(it))
                }

            }
        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent) {
            when(intent) {
                is Intent.ClickSearch -> publish(Label.ClickSearch)
                is Intent.ClickToFavourite -> publish(Label.ClickToFavourite)
                is Intent.SelectCity -> publish(Label.SelectCity(intent.cityId))
            }
        }

        override fun executeAction(action: Action) {
            when(action) {
                is Action.FavoriteCitiesLoaded -> {
                    val cities = action.cities
                    dispatch(Msg.FavoriteCitiesLoaded(cities))
                    cities.forEach { city ->
                        scope.launch {
                            loadWeatherForCity(city)
                        }
                    }
                }
            }
        }

        private suspend fun loadWeatherForCity(city: City) {
            dispatch(Msg.WeatherIsLoading(city.id))
            try {
                val weather = getCurrentWeatherUseCase(city.id)
                dispatch(
                    Msg.WeatherLoaded(
                        cityId = city.id,
                        tempC = weather.tempC,
                        conditionUrl = weather.conditionUrl
                    )
                )
            } catch (e: Exception) {
                dispatch(Msg.WeatherLoadingError(cityId = city.id))
            }
        }

    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State = when(msg) {
            is Msg.FavoriteCitiesLoaded -> {
                copy(
                    cityItems = msg.cities.map {
                        State.CityItem(
                            city = it,
                            weatherState = State.WeatherState.Initial
                        )
                    }
                )
            }
            is Msg.WeatherIsLoading -> {
                copy(
                    cityItems = cityItems.map {
                        if (it.city.id == msg.cityId) {
                            it.copy(weatherState = State.WeatherState.Loading)
                        } else {
                            it
                        }
                    }
                )
            }
            is Msg.WeatherLoaded -> {
                copy(
                    cityItems = cityItems.map {
                        if (it.city.id == msg.cityId) {
                            it.copy(weatherState = State.WeatherState.Loaded(msg.tempC, msg.conditionUrl))
                        } else {
                            it
                        }
                    }
                )
            }
            is Msg.WeatherLoadingError -> {
                copy(
                    cityItems = cityItems.map {
                        if (it.city.id == msg.cityId) {
                            it.copy(weatherState = State.WeatherState.Error)
                        } else {
                            it
                        }
                    }
                )
            }
        }
    }
}
