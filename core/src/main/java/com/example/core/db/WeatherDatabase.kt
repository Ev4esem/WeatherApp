package com.example.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.core.models.CityEntity

@Database(entities = [CityEntity::class], version = 1, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {

    abstract fun favouriteCitiesDao(): FavouriteCitiesDao

    companion object {

        private const val DB_NAME = "WeatherDatabase"
        private var INSTANCE: WeatherDatabase? = null
        private val LOCK = Any()


        fun getInstance(context: Context): WeatherDatabase {
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

    }

}