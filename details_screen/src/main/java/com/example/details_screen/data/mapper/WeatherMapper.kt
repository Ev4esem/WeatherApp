package com.example.details_screen.data.mapper

import com.example.details_screen.data.network.dto.WeatherCurrentDto
import com.example.details_screen.data.network.dto.WeatherDto
import com.example.details_screen.data.network.dto.WeatherForecastDto
import com.example.details_screen.domain.entities.Forecast
import com.example.details_screen.domain.entities.Weather
import java.util.Calendar
import java.util.Date

fun WeatherCurrentDto.toWeatherCurrent(): Weather = current.toWeather()

fun WeatherDto.toWeather(): Weather = Weather(
   tempC = tempC,
   conditionText = conditionDto.text,
   conditionUrl = conditionDto.iconUrl.correctImageUrl(),
   date = date.toCalendar()
)

private fun Long.toCalendar(): Calendar = Calendar.getInstance().apply {
    time = Date(this@toCalendar * 1000)
}

private fun String.correctImageUrl() = "https:$this".replace(oldValue = "64x64", newValue = "128x128")

fun WeatherForecastDto.toForecast(): Forecast = Forecast(
    currentWeather = current.toWeather(),
    upcoming = forecastDto.forecastDay.drop(1).map { dayDto ->
        val dayWeatherDto = dayDto.dayWeatherDto
        Weather(
            tempC = dayWeatherDto.tempC,
            conditionText = dayWeatherDto.conditionDto.text,
            conditionUrl = dayWeatherDto.conditionDto.iconUrl.correctImageUrl(),
            date = dayDto.date.toCalendar()
        )
    }
)
