package com.example.favourite_screen.data.mapper

import com.example.favourite_screen.data.local.models.CityEntity
import com.example.favourite_screen.domain.entities.City

fun City.toCityEntity(): CityEntity = CityEntity(id, name, country)

fun CityEntity.toCity(): City = City(id, name, country)

fun List<CityEntity>.toCities(): List<City> = map { it.toCity() }