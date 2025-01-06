package com.example.search_screen.data.network.mapper

import com.example.core.models.City
import com.example.search_screen.data.network.dto.CityDto

fun CityDto.toCity(): City = City(id, name, country)

fun List<CityDto>.toCities(): List<City> = map { it.toCity() }