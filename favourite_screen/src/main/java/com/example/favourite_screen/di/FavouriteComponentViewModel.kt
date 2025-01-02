package com.example.favourite_screen.di

import androidx.lifecycle.ViewModel
import kotlin.properties.Delegates.notNull

class FavouriteComponentViewModel: ViewModel() {

    internal val favouriteComponent = DaggerFavouriteComponent.factory().create(
       deps =  FavouriteDependenciesProvider.deps
    )

}

interface FavouriteDependenciesProvider {

    var deps: FavouriteDependencies

    companion object : FavouriteDependenciesProvider by FavouriteDependenciesStore

}

object FavouriteDependenciesStore : FavouriteDependenciesProvider {

    override var deps: FavouriteDependencies by notNull()

}