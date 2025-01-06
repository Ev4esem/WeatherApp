plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.parcelize.get().pluginId)
    alias(libs.plugins.ksp)
}

apply<LibraryGradlePlugin>()

android {
    namespace = "com.example.core"
    kotlinOptions {
        jvmTarget = ProjectConfig.jvmTargetVersion
    }
    buildFeatures {
        compose = false
    }
}

dependencies {
    dagger()
    room()
    decompose()
}