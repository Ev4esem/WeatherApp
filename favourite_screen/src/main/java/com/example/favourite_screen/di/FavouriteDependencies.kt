package com.example.favourite_screen.di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import retrofit2.Retrofit

interface FavouriteDependencies {
    val storeFactory: StoreFactory
    val retrofit: Retrofit
}