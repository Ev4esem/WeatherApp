package com.example.details_screen.di

import com.example.details_screen.data.DetailsRepositoryImpl
import com.example.details_screen.data.network.api.DetailsApiService
import com.example.details_screen.domain.repositories.DetailsRepository
import com.example.details_screen.presentation.DetailsComponent
import com.example.details_screen.presentation.DetailsComponentImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
interface DetailsModule {

    @[Singleton Binds]
    fun bindRepositoryToRepositoryImpl(repositoryImpl: DetailsRepositoryImpl): DetailsRepository

    @Binds
    fun componentFactory(componentFactoryImpl: DetailsComponentImpl.Factory): DetailsComponent.Factory

    companion object {
        @[Singleton Provides]
        fun provideDetailsApiService(retrofit: Retrofit): DetailsApiService {
           return retrofit.create()
        }
    }

}