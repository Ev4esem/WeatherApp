package com.example.core.di.common

interface DaggerComponentDepsProvider<D: DaggerComponentDeps> {
    val deps: D
}