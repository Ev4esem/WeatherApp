package com.example.search_screen.di

import androidx.lifecycle.ViewModel
import kotlin.properties.Delegates.notNull

class SearchComponentViewModel: ViewModel() {

    internal val searchComponent = DaggerSearchComponent.factory().create(
       deps =  SearchDependenciesProvider.deps
    )

}

interface SearchDependenciesProvider {

    var deps: SearchDependencies

    companion object : SearchDependenciesProvider by SearchDependenciesStore

}

object SearchDependenciesStore : SearchDependenciesProvider {

    override var deps: SearchDependencies by notNull()

}