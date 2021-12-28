import Version.composeCompiler

plugins {
    id(Plugin.androidApplication)
    kotlin(Plugin.android)
}

android {
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        applicationId = AndroidConfig.applicationId
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk
        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName

        vectorDrawables {
            useSupportLibrary = AndroidConfig.useSupportLibrary
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = composeCompiler
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Modules
    implementation(project(ModuleDependency.Core.ui))

    implementation(project(ModuleDependency.UI.movieDetails))
    implementation(project(ModuleDependency.UI.home))

    implementation(project(ModuleDependency.Feature.Tmdb.impl))
    implementation(project(ModuleDependency.Feature.AppPrefenrences.api))
    implementation(project(ModuleDependency.Feature.AppPrefenrences.impl))

    // Libraries
    implementation(Dependency.AndroidX.core)
    implementation(Dependency.AndroidX.appcompat)
    implementation(Dependency.AndroidX.material)
    implementation(Dependency.AndroidX.activityCompose)

    api(Dependency.AndroidX.Compose.compiler)
    api(Dependency.AndroidX.Compose.ui)
    api(Dependency.AndroidX.Compose.tooling)
    api(Dependency.AndroidX.Compose.toolingPreview)
    api(Dependency.AndroidX.Compose.runtime)
    api(Dependency.AndroidX.Compose.foundation)
    api(Dependency.AndroidX.Compose.material)

    implementation(Dependency.Koin.android)
    implementation(Dependency.Koin.compose)

    implementation(Dependency.Loggers.prettyLogger)
    implementation(Dependency.Loggers.timber)

    testImplementation(Dependency.Koin.tests)
}
