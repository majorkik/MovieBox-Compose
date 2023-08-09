plugins {
    id("moviebox.application.compose")
    id("moviebox.code.quality")
    id("moviebox.gradle.versions")
    alias(libs.plugins.ksp)
}

android {
    defaultConfig {
        applicationId = "com.majorkik.movieboxcompose"
        versionCode = 1
        versionName = "0.0.1"
    }

    buildFeatures {
        buildConfig = true
    }

    namespace = "com.majorkik.movieboxcompose"

    applicationVariants.all {
        kotlin.sourceSets {
            getByName(name) { kotlin.srcDir("build/generated/ksp/$name/kotlin") }
        }
    }
}

dependencies {
    implementation(projects.core.ui)
    implementation(projects.core.localizaton)

    implementation(projects.ui.authorization)
    implementation(projects.ui.details)
    implementation(projects.ui.navHome)
    implementation(projects.ui.navProfile)
    implementation(projects.ui.navSearch)

    implementation(projects.feature.tmdb.api)
    implementation(projects.feature.tmdb.impl)
    implementation(projects.feature.appPreferences.api)
    implementation(projects.feature.appPreferences.impl)

    implementation(libs.bundles.androidx.ui)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.logging)
    implementation(libs.androidx.compose.material3)

    implementation(libs.koin.android)
    implementation(libs.koin.compose)

    implementation(libs.accompanist.navigation.animation)
    implementation(libs.accompanist.navigation.material)
    implementation(libs.accompanist.insets)
    implementation(libs.accompanist.ui.controller)

    implementation(libs.compose.destinations.core)

    testImplementation(libs.koin.test)

    ksp(libs.compose.destinations.ksp)
}
