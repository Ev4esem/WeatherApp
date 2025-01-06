package com.example.weatherapp.di

import android.content.Context
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.example.core.db.FavouriteCitiesDao
import com.example.details_screen.di.DetailsDependencies
import com.example.core.di.DatabaseDependencies
import com.example.favourite_screen.di.FavouriteDependencies
import com.example.search_screen.di.SearchDependencies
import com.example.weatherapp.MainActivity
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class, PresentationModule::class]
)
interface AppComponent:
    SearchDependencies,
    FavouriteDependencies,
    DetailsDependencies,
    DatabaseDependencies {

    override val favouriteCitiesDao: FavouriteCitiesDao

    override val retrofit: Retrofit

    override val context: Context

    override val storeFactory: StoreFactory

    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(context: Context): Builder

        fun build(): AppComponent
    }

}
