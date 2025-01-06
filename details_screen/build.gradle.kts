plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.parcelize.get().pluginId)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.compose)
}

apply<LibraryGradlePlugin>()

android {
    namespace = "com.example.details_screen"
    kotlinOptions {
        jvmTarget = ProjectConfig.jvmTargetVersion
    }
}

dependencies {
    compose()
    test()
    dagger()
    kotlin()
    retrofit()
    decompose()
    mvikotlin()
    implementation(Dependencies.lifecycleViewModelCompose)
    implementation(project(Modules.CORE_MODULE))
}