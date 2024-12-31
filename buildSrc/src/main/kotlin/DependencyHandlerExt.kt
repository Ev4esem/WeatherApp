import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependency: String) {
    add("implementation", dependency)
}

fun DependencyHandler.implementation(dependency: Dependency) {
    add("implementation", dependency)
}

fun DependencyHandler.androidTest(dependency: String) {
    add("androidTest", dependency)
}

fun DependencyHandler.debugImplementation(dependency: String) {
    add("debugImplementation", dependency)
}

fun DependencyHandler.test(dependency: String) {
    add("test", dependency)
}

fun DependencyHandler.ksp(dependency: String) {
    add("ksp", dependency)
}

fun DependencyHandler.androidTestImplementation(dependency: String) {
    add("androidTestImplementation", dependency)
}

fun DependencyHandler.testImplementation(dependency: String) {
    add("testImplementation", dependency)
}

fun DependencyHandler.room() {
    implementation(Dependencies.roomKtx)
    ksp(Dependencies.roomCompiler)
}

fun DependencyHandler.decompose() {
    implementation(Dependencies.decomposeCompose)
    implementation(Dependencies.decompose)
}

fun DependencyHandler.kotlin() {
    implementation(Dependencies.coreKtx)
}

fun DependencyHandler.compose() {
    implementation(Dependencies.activityCompose)
    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.ui)
    implementation(Dependencies.uiTooling)
    implementation(Dependencies.uiToolingPreview)
    implementation(Dependencies.uiGraphics)
    implementation(Dependencies.material3)
    implementation(Dependencies.material)
    implementation(Dependencies.materialIconsExtended)
    implementation(Dependencies.lifecycleRuntimeKtx)
    debugImplementation(Dependencies.uiTooling)
    debugImplementation(Dependencies.uiTestManifest)
    androidTestImplementation(Dependencies.composeBom)
}

fun DependencyHandler.test() {
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.androidxJunit)
    androidTestImplementation(Dependencies.espressoCore)

}

fun DependencyHandler.mvikotlin() {
    implementation(Dependencies.mvikotlinMain)
    implementation(Dependencies.mvikotlin)
    implementation(Dependencies.mvikotlinExtensionsCoroutines)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.retrofitConverter)
    implementation(Dependencies.retrofit)
}

fun DependencyHandler.dagger() {
    implementation(Dependencies.dagger)
    ksp(Dependencies.daggerCompiler)
}