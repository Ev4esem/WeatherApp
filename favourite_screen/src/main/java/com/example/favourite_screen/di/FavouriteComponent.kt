package com.example.favourite_screen.di

import dagger.Component

@Component(
    modules = [FavouriteModule::class],
    dependencies = [FavouriteDependencies::class]
)
internal interface FavouriteComponent {

    @Component.Factory
    interface Factory {
        fun create(
            deps: FavouriteDependencies
        ): FavouriteComponent
    }
}