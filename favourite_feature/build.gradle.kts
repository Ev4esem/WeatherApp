plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.compose)
}

apply<MainGradlePlugin>()

android {
    namespace = "com.example.favourite_feature"
    kotlinOptions {
        jvmTarget = ProjectConfig.jvmTargetVersion
    }
}

dependencies {
    compose()
    test()
    dagger()
    retrofit()
    kotlin()
    decompose()
    mvikotlin()
}