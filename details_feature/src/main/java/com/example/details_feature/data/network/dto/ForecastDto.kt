package com.example.details_feature.data.network.dto

import com.google.gson.annotations.SerializedName

data class ForecastDto(
    @SerializedName("forecastday") val forecastDay: List<DayDto>,
)
