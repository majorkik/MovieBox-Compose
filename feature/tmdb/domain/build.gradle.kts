plugins {
    kotlin("jvm")
    id("moviebox.code.quality")
}

dependencies {
    implementation(projects.core.common)

    implementation(libs.koin.core)

    implementation(platform(libs.arrow.bom))
    implementation(libs.arrow.core)

    implementation(libs.bundles.logging)
    implementation(libs.klock)
}
