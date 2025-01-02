package com.example.details_screen.di

import dagger.Component

@Component(
    modules = [DetailsModule::class],
    dependencies = [DetailsDependencies::class]
)
internal interface DetailsComponent {

    @Component.Factory
    interface Factory {
        fun create(
            deps: DetailsDependencies
        ): DetailsComponent
    }
}