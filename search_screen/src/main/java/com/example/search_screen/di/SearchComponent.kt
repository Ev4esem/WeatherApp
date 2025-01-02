package com.example.search_screen.di

import dagger.Component

@Component(
    modules = [SearchModule::class],
    dependencies = [SearchDependencies::class]
)
internal interface SearchComponent {

    @Component.Factory
    interface Factory {
        fun create(
            deps: SearchDependencies
        ): SearchComponent
    }
}