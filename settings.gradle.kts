pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "com.android.application",
                "com.android.library" ->
                    useModule("com.android.tools.build:gradle:${requested.version}")
                "koin" -> useModule("org.koin:koin-gradle-plugin:${requested.version}")
                "com.diffplug.spotless" ->
                    useModule("com.diffplug.spotless:spotless-plugin-gradle:${requested.version}")
            }
        }
    }
}

rootProject.buildFileName = "build.gradle.kts"

include(":app")
include(":core_network", ":core_ui")
include(":feature_navigation", ":feature_discover", ":feature_details", ":feature_collections")
