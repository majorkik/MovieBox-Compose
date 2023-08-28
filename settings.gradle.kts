pluginManagement {
    includeBuild("composite-build")

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver") version "0.4.0"
}

toolchainManagement {
    jvm {
        javaRepositories {
            repository("foojay") {
                resolverClass.set(org.gradle.toolchains.foojay.FoojayToolchainResolver::class.java)
            }
        }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "moviebox-compose"

// Main module
include(":app")
// Core modules
include(":core:ui", ":core:common", ":core:localizaton")
// UI
include(
    ":ui:nav-home",
    ":ui:nav-search",
    ":ui:nav-profile",
    ":ui:details",
    ":ui:authorization"
)
// Features
include(
    ":feature:tmdb:domain",
    ":feature:tmdb:data",
    ":feature:app-preferences:api",
    ":feature:app-preferences:impl",
)
