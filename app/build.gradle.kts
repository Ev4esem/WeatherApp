plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.parcelize.get().pluginId)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
}

apply<MainGradlePlugin>()

android {
    namespace = "com.example.weatherapp"
    defaultConfig {
        versionCode = ProjectConfig.versionCode
        targetSdk = ProjectConfig.targetSdk
        versionName = ProjectConfig.versionName
        applicationId = ProjectConfig.appId
        val key = property("apikey")?.toString() ?: error("You should add apikey in gradle.properties")
        buildConfigField("String", "WEATHER_API_KEY", "\"$key\"")
    }
    kotlinOptions {
        jvmTarget = ProjectConfig.jvmTargetVersion
    }
    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    kotlin()
    compose()
    mvikotlin()
    decompose()
    room()
    dagger()
    implementation(Dependencies.glideCompose)
    implementation(project(Modules.DETAILS_MODULE))
    implementation(project(Modules.SEARCH_MODULE))
    implementation(project(Modules.FAVOURITE_MODULE))
    retrofit()
    test()
}