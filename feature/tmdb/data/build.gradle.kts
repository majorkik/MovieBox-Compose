plugins {
    kotlin("jvm")
    id("kotlinx-serialization")
    id("moviebox.code.quality")
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.feature.tmdb.domain)

    implementation(libs.bundles.logging)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.coroutines.core)

    implementation(libs.koin.core)

    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp.bom.core)
    implementation(libs.okhttp.bom.interceptor)

    implementation(platform(libs.arrow.bom))
    implementation(libs.arrow.core)

    implementation(libs.eithernet)

    implementation(libs.serialization.converter)

    implementation(libs.klock)
}
