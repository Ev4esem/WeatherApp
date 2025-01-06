package com.example.favourite_screen.di

import com.example.favourite_screen.data.FavouriteRepositoryImpl
import com.example.favourite_screen.data.network.api.FavouriteApiService
import com.example.favourite_screen.domain.repositories.FavouriteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
interface FavouriteModule {

    @Binds
    fun bindRepositoryToRepositoryImpl(repositoryImpl: FavouriteRepositoryImpl): FavouriteRepository

    companion object {
        @Provides
        fun provideFavouriteApiService(retrofit: Retrofit): FavouriteApiService = retrofit.create()
    }

}