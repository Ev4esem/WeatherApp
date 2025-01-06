package com.example.details_screen.di

import com.example.details_screen.data.DetailsRepositoryImpl
import com.example.details_screen.data.network.api.DetailsApiService
import com.example.details_screen.domain.repositories.DetailsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
interface DetailsModule {

    @[DetailsScreenScope Binds]
    fun bindRepositoryToRepositoryImpl(repositoryImpl: DetailsRepositoryImpl): DetailsRepository

    companion object {
        @[DetailsScreenScope Provides]
        fun provideDetailsApiService(retrofit: Retrofit): DetailsApiService {
           return retrofit.create()
        }
    }

}