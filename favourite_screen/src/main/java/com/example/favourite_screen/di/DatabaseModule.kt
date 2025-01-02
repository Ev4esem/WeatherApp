package com.example.favourite_screen.di

import android.content.Context
import androidx.room.Room
import com.example.favourite_screen.data.local.db.FavouriteCitiesDao
import com.example.favourite_screen.data.local.db.FavouriteDatabase
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {

    private const val DB_NAME = "FavouriteDatabase"
    private var INSTANCE: FavouriteDatabase? = null
    private val LOCK = Any()

    @Provides
    fun provideDatabase(context: Context): FavouriteDatabase {
        INSTANCE?.let { return it }
        synchronized(LOCK) {
            INSTANCE?.let { return it }
            val database = Room.databaseBuilder(
                context = context,
                klass = FavouriteDatabase::class.java,
                name = DB_NAME,
            ).build()
            INSTANCE = database
            return database
        }
    }

    @Provides
    fun bindFavouriteCitiesDao(database: FavouriteDatabase): FavouriteCitiesDao =
        database.favouriteCitiesDao()

}