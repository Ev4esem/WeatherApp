package com.example.favourite_screen.di

import dagger.Component

@Component(
    modules = [DatabaseModule::class, FavouriteModule::class],
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