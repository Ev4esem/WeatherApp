package com.example.core.di

import android.content.Context
import com.example.core.di.common.DaggerComponentDeps

interface DatabaseDependencies: DaggerComponentDeps {

    val context: Context

}