plugins {
    `kotlin-dsl`
}

group = "com.majorkik.movieboxcompose"

repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

gradlePlugin {
    plugins.register("android-application-compose-plugin") {
        id = "moviebox.application.compose"
        implementationClass = "AndroidApplicationComposePlugin"
    }

    plugins.register("android-application-plugin") {
        id = "moviebox.application"
        implementationClass = "AndroidApplicationPlugin"
    }

    plugins.register("android-library-compose-plugin") {
        id = "moviebox.library.compose"
        implementationClass = "AndroidLibraryComposePlugin"
    }

    plugins.register("android-library-plugin") {
        id = "moviebox.library"
        implementationClass = "AndroidLibraryPlugin"
    }

    plugins.register("code-quality-plugin") {
        id = "moviebox.code.quality"
        implementationClass = "CodeQualityPlugin"
    }

    plugins.register("gradle-versions-plguin") {
        id = "moviebox.gradle.versions"
        implementationClass = "GradleVersionPlugin"
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)

    implementation(libs.detekt.plugin)
    implementation(libs.spotless.plugin)
    implementation(libs.ktlint.jlleitschuh.plugin)
    implementation(libs.gradle.versions.plugin)
}
