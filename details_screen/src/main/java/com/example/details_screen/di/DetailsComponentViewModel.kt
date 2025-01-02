package com.example.details_screen.di

import androidx.lifecycle.ViewModel
import kotlin.properties.Delegates.notNull

class DetailsComponentViewModel: ViewModel() {

    internal val detailsComponent = DaggerDetailsComponent.factory().create(
        deps =  DetailsDependenciesProvider.deps
    )

}

interface DetailsDependenciesProvider {

    var deps: DetailsDependencies

    companion object : DetailsDependenciesProvider by DetailsDependenciesStore

}

object DetailsDependenciesStore : DetailsDependenciesProvider {

    override var deps: DetailsDependencies by notNull()

}