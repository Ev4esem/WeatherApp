package com.example.core.di

import com.example.core.di.common.DaggerComponentDepsProvider
import kotlin.properties.Delegates.notNull

object DatabaseStore: DaggerComponentDepsProvider<DatabaseDependencies> {

    override var deps: DatabaseDependencies by notNull()

}