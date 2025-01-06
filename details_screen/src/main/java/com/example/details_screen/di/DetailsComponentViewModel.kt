package com.example.details_screen.di

import androidx.lifecycle.ViewModel
import com.example.core.di.common.DaggerComponentDepsProvider
import kotlin.properties.Delegates.notNull

class DetailsComponentViewModel: ViewModel() {
    internal val detailsComponent = DaggerDetailsComponent.factory().create(
        deps =  DetailsDependenciesStore.deps
    )
}

object DetailsDependenciesStore : DaggerComponentDepsProvider<DetailsDependencies> {

    override var deps: DetailsDependencies by notNull()

}