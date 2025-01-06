package com.example.search_screen.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.example.core.extensions.componentScope
import com.example.core.models.City
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchComponentImpl @AssistedInject constructor(
    private val searchStoreFactory: SearchStoreFactory,
    @Assisted("openReason") private val openReason: OpenReason,
    @Assisted("onBackClicked") private val onBackClicked: () -> Unit,
    @Assisted("onClickForecast") private val onClickForecast: (City) -> Unit,
    @Assisted("onClickFavourite") private val onClickFavourite: () -> Unit,
    @Assisted("componentContext") componentContext: ComponentContext
) : SearchComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { searchStoreFactory.create(openReason) }
    private val scope = componentScope()

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<SearchStore.State> = store.stateFlow

    init {
        scope.launch {
            store.labels.collect {
                when(it) {
                    is SearchStore.Label.ClickBack -> {
                        onBackClicked()
                    }
                    is SearchStore.Label.OpenForecast -> {
                        onClickForecast(it.city)
                    }
                    is SearchStore.Label.SavedToFavourite -> {
                        onClickFavourite()
                    }
                }
            }
        }
    }

    override fun changeSearchQuery(query: String) {
        store.accept(SearchStore.Intent.ChangeSearchQuery(query))
    }

    override fun onClickBack() {
        store.accept(SearchStore.Intent.ClickBack)
    }

    override fun onClickSearch() {
        store.accept(SearchStore.Intent.ClickSearch)
    }

    override fun onClickCity(city: City) {
        store.accept(SearchStore.Intent.ClickCity(city))
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("openReason") openReason: OpenReason,
            @Assisted("onBackClicked") onBackClicked: () -> Unit,
            @Assisted("onClickForecast") onClickForecast: (City) -> Unit,
            @Assisted("onClickFavourite") onClickFavourite: () -> Unit,
            @Assisted("componentContext") componentContext: ComponentContext
        ): SearchComponentImpl
    }

}