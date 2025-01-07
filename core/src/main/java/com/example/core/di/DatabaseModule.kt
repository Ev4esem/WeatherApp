package com.example.core.di

import android.content.Context
import androidx.room.Room
import com.example.core.db.FavouriteCitiesDao
import com.example.core.db.WeatherDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    private const val DB_NAME = "WeatherDatabase"
    private var INSTANCE: WeatherDatabase? = null
    private val LOCK = Any()

    @[Singleton Provides]
    fun provideDatabase(context: Context): WeatherDatabase {
        INSTANCE?.let { return it }
        synchronized(LOCK) {
            INSTANCE?.let { return it }
            val database = Room.databaseBuilder(
                context = context,
                klass = WeatherDatabase::class.java,
                name = DB_NAME,
            ).build()
            INSTANCE = database
            return database
        }
    }

    @[Singleton Provides]
    fun bindFavouriteCitiesDao(db: WeatherDatabase): FavouriteCitiesDao =
        db.favouriteCitiesDao()

}