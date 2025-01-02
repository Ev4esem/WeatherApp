package com.example.favourite_screen.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.favourite_screen.data.local.models.CityEntity

@Database(entities = [CityEntity::class], version = 1, exportSchema = false)
abstract class FavouriteDatabase: RoomDatabase() {

    abstract fun favouriteCitiesDao(): FavouriteCitiesDao

}