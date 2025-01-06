package com.example.details_screen.di

import com.example.core.di.DatabaseModule
import com.example.details_screen.presentation.DetailsStoreFactory
import dagger.Component

@DetailsScreenScope
@Component(
    modules = [DetailsModule::class, DatabaseModule::class],
    dependencies = [DetailsDependencies::class],

)
interface DetailsComponent {

    fun inject(storeFactory: DetailsStoreFactory)

    @Component.Factory
    interface Factory {
        fun create(
            deps: DetailsDependencies
        ): DetailsComponent
    }
}