package com.example.favourite_screen.di

import com.example.favourite_screen.data.FavouriteRepositoryImpl
import com.example.favourite_screen.domain.repositories.FavouriteRepository
import dagger.Binds
import dagger.Module

@Module
interface FavouriteModule {

    @Binds
    fun bindRepositoryToRepositoryImpl(repositoryImpl: FavouriteRepositoryImpl): FavouriteRepository

}