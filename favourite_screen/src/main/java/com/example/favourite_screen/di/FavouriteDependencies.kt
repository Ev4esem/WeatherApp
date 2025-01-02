package com.example.favourite_screen.di

import android.content.Context
import com.arkivanov.mvikotlin.core.store.StoreFactory

interface FavouriteDependencies {
    val context: Context
    val storeFactory: StoreFactory
}