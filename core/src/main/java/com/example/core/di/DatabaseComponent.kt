package com.example.core.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [DatabaseDependencies::class],
    modules = [DatabaseModule::class]
)
interface DatabaseComponent