package com.example.favourite_screen.di

import com.example.favourite_screen.data.FavouriteRepositoryImpl
import com.example.favourite_screen.data.network.api.FavouriteApiService
import com.example.favourite_screen.domain.repositories.FavouriteRepository
import com.example.favourite_screen.presentation.FavouriteComponent
import com.example.favourite_screen.presentation.FavouriteComponentImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
interface FavouriteModule {

    @Binds
    fun componentFactory(componentFactoryImpl: FavouriteComponentImpl.Factory): FavouriteComponent.Factory

    @[Singleton Binds]
    fun bindRepositoryToRepositoryImpl(repositoryImpl: FavouriteRepositoryImpl): FavouriteRepository

    companion object {
        @[Singleton Provides]
        fun provideFavouriteApiService(retrofit: Retrofit): FavouriteApiService = retrofit.create()
    }

}