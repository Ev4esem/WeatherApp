package com.example.core.di

import android.content.Context
import com.example.core.db.FavouriteCitiesDao
import com.example.core.db.WeatherDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun bindFavouriteCitiesDao(context: Context): FavouriteCitiesDao =
        WeatherDatabase.getInstance(context).favouriteCitiesDao()

}