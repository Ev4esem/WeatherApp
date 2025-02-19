package com.example.core.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_cities")
data class CityEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val country: String,
)
