plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.compose)
}

apply<LibraryGradlePlugin>()

android {
    namespace = "com.example.search_screen"
    kotlinOptions {
        jvmTarget = ProjectConfig.jvmTargetVersion
    }
}

dependencies {
    compose()
    kotlin()
    dagger()
    retrofit()
    test()
    decompose()
    mvikotlin()
    implementation(project(Modules.CORE_MODULE))
}