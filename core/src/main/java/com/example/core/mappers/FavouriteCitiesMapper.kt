package com.example.core.mappers

import com.example.core.models.City
import com.example.core.models.CityEntity

fun City.toCityEntity(): CityEntity =
    CityEntity(id, name, country)

fun CityEntity.toCity(): City = City(id, name, country)

fun List<CityEntity>.toCities(): List<City> = map { it.toCity() }