package com.example.search_screen.di

import com.example.search_screen.data.SearchRepositoryImpl
import com.example.search_screen.data.network.api.SearchApiService
import com.example.search_screen.domain.repositories.SearchRepository
import com.example.search_screen.presentation.SearchComponent
import com.example.search_screen.presentation.SearchComponentImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
interface SearchModule {

    @Binds
    fun componentFactory(componentFactoryImpl: SearchComponentImpl.Factory): SearchComponent.Factory

    @[Singleton Binds]
    fun bindRepositoryToRepositoryImpl(repositoryImpl: SearchRepositoryImpl): SearchRepository

    companion object {
        @[Singleton Provides]
        fun provideSearchApiService(retrofit: Retrofit): SearchApiService {
            return retrofit.create()
        }
    }

}