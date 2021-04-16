

plugins {
    id(Plugins.androidLibrary)
    kotlin(Plugins.android)
}

android {
    compileSdkVersion(AndroidConfig.compileSdk)

    defaultConfig {
        minSdkVersion(AndroidConfig.minSdk)
        targetSdkVersion(AndroidConfig.targetSdk)
        buildToolsVersion(AndroidConfig.buildTools)

        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName
    }

    buildFeatures {
        buildConfig = false // Disable generation of BuildConfig files in modules where they are not needed

        aidl = false
        prefab = false
        shaders = false

        compose = false

        dataBinding = false
        viewBinding = false
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            isDebuggable = BuildTypeRelease.isDebuggable
            proguardFiles("proguard-android.txt", "proguard-rules.pro")
        }

        getByName(BuildType.DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            isDebuggable = BuildTypeDebug.isDebuggable
        }
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    api(Libs.Network.OkHttp3.OKHTTP_3)
    api(Libs.Network.OkHttp3.LOGGING_INTERCEPTOR)

    api(Libs.Network.Retrofit.retrofitLib)
    api(Libs.Network.Retrofit.retrofitMoshi)
}
