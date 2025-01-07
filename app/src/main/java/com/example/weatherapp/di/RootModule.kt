package com.example.weatherapp.di

import com.example.core.di.DatabaseModule
import com.example.details_screen.di.DetailsModule
import com.example.favourite_screen.di.FavouriteModule
import com.example.search_screen.di.SearchModule
import dagger.Module

@Module(
    includes = [
        DetailsModule::class,
        FavouriteModule::class,
        SearchModule::class,
        DatabaseModule::class,
        PresentationModule::class,
        NetworkModule::class
    ]
)
class RootModule {

}