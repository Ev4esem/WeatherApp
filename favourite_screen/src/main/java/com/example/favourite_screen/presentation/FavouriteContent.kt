package com.example.favourite_screen.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.core.models.City

@Composable
fun FavouriteContent(component: FavouriteComponent) {

    val state by component.model.collectAsState()

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2)
    ) {
        items(
            items = state.cityItems,
            key = { it.city.id }
        ) {
            CityCard(
                cityItem = it
            )
        }
    }
}

@Composable
private fun CityCard(
    cityItem: FavouriteStore.State.CityItem
) {
    Card(
        modifier = Modifier.fillMaxSize(),
        colors = CardDefaults.cardColors(contentColor = Color.Blue)
    ) {
        Text(
            text = cityItem.city.name
        )
    }
}