package com.example.search_screen.presentation

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.core.models.City
import com.example.search_screen.domain.usecases.AddToFavouriteUseCase
import com.example.search_screen.domain.usecases.SearchCityUseCase
import com.example.search_screen.presentation.SearchStore.Intent
import com.example.search_screen.presentation.SearchStore.Label
import com.example.search_screen.presentation.SearchStore.State
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

interface SearchStore : Store<Intent, State, Label> {

    sealed interface Intent {

        data class ChangeSearchQuery(val query: String): Intent

        data object ClickBack: Intent

        data object ClickSearch: Intent

        data class ClickCity(val city: City): Intent

    }

    data class State(
        val searchQuery: String,
        val searchState: SearchState
    ) {

        sealed interface SearchState {

            data object Initial: SearchState

            data object Loading: SearchState

            data object Error: SearchState

            data object EmptyResult: SearchState

            data class SuccessLoaded(val cities: List<City>) : SearchState

        }

    }

    sealed interface Label {

        data object ClickBack: Label

        data object SavedToFavourite: Label

        data class OpenForecast(val city: City): Label

    }
}

class SearchStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val searchCityUseCase: SearchCityUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase,
) {

    fun create(openReason: OpenReason): SearchStore =
        object : SearchStore, Store<Intent, State, Label> by storeFactory.create(
            name = "FavouriteStore",
            initialState = State(
                searchQuery = "",
                searchState = State.SearchState.Initial,
            ),
            bootstrapper = BootstrapperImpl(),
            executorFactory = { ExecutorImpl(openReason) },
            reducer = ReducerImpl
        ) {}

    private sealed interface Action

    private sealed interface Msg {

        data class ChangeSearchQuery(val query: String): Msg

        data object LoadingSearchResult: Msg

        data object SearchResultError: Msg

        data class SearchResultLoaded(val cities: List<City>): Msg

    }

    private class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {}
    }

    private inner class ExecutorImpl(
        private val openReason: OpenReason
    ) : CoroutineExecutor<Intent, Action, State, Msg, Label>() {

        private var searchJob: Job? = null

        override fun executeIntent(intent: Intent) {
            when(intent) {
                is Intent.ChangeSearchQuery -> {
                    dispatch(Msg.ChangeSearchQuery(intent.query))
                }
                is Intent.ClickBack -> {
                    publish(Label.ClickBack)
                }
                is Intent.ClickCity -> {
                    when(openReason) {
                        OpenReason.AddToFavourite -> {
                            scope.launch {
                                addToFavouriteUseCase(intent.city)
                                publish(Label.SavedToFavourite)
                            }
                        }
                        OpenReason.RegularSearch -> {
                            publish(Label.OpenForecast(intent.city))
                        }
                    }
                }
                is Intent.ClickSearch -> {
                    searchJob?.cancel()
                    searchJob = scope.launch {
                        dispatch(Msg.LoadingSearchResult)
                        try {
                            val cities = searchCityUseCase(state().searchQuery)
                            dispatch(Msg.SearchResultLoaded(cities))
                        } catch (e: Exception) {
                            dispatch(Msg.SearchResultError)
                        }
                    }
                }
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State = when(msg) {
            is Msg.ChangeSearchQuery ->  {
                copy(
                    searchQuery = msg.query
                )
            }
            is Msg.LoadingSearchResult -> {
                copy(
                    searchState = State.SearchState.Loading
                )
            }
            is Msg.SearchResultError ->  {
                copy(
                    searchState = State.SearchState.Error
                )
            }
            is Msg.SearchResultLoaded ->  {
                copy(
                    searchState = if (msg.cities.isEmpty()) {
                        State.SearchState.EmptyResult
                    } else {
                        State.SearchState.SuccessLoaded(msg.cities)
                    }
                )
            }
        }
    }
}
