package com.example.details_screen.di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.example.core.db.FavouriteCitiesDao
import com.example.core.di.common.DaggerComponentDeps
import retrofit2.Retrofit

interface DetailsDependencies: DaggerComponentDeps {
    val favouriteCitiesDao: FavouriteCitiesDao
    val retrofit: Retrofit
    val storeFactory: StoreFactory
}