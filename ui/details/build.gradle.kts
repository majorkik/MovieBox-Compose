plugins {
    id("moviebox.library.compose")
    id("moviebox.code.quality")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.majorkik.ui.details"

    libraryVariants.all {
        kotlin.sourceSets {
            getByName(name) { kotlin.srcDir("build/generated/ksp/$name/kotlin") }
        }
    }

    ksp {
        arg("compose-destinations.moduleName", "details")
        arg("compose-destinations.mode", "destinations")
    }
}

dependencies {
    // Submodules
    implementation(projects.core.ui)
    implementation(projects.core.common)
    implementation(projects.feature.tmdb.api)
    implementation(projects.core.localizaton)

    // Android
    implementation(libs.mvi.orbit)
    implementation(libs.coil)
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)
    implementation(libs.compose.destinations.core)
    implementation(libs.expandabletext)

    // Functional programming in Kotlin
    implementation(platform(libs.arrow.bom))
    implementation(libs.arrow.core)

    // DI
    implementation(libs.koin.compose)

    // Utils
    implementation(libs.bundles.logging)
    implementation(libs.klock)

    // KSP
    ksp(libs.compose.destinations.ksp)
}
