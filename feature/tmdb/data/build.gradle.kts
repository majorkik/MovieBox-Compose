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

    implementation(libs.kotlinx.immutablelist)

    implementation(libs.koin.core)

    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp.bom.core)
    implementation(libs.okhttp.bom.interceptor)

    implementation(libs.ktor.core)
    implementation(libs.ktor.okhttp)
    implementation(libs.ktor.logging)
    implementation(libs.ktor.json)
    implementation(libs.ktor.negotiation)
    implementation(libs.ktor.resource)

    implementation(platform(libs.arrow.bom))
    implementation(libs.arrow.core)

    implementation(libs.eithernet)

    implementation(libs.serialization.converter)

    implementation(libs.klock)

    testImplementation(libs.ktor.mock)
    testImplementation(libs.kotest.junit)
    testImplementation(libs.kotest.assertions)
    testImplementation(libs.mockk)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
