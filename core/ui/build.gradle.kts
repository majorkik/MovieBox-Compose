plugins {
    id("moviebox.library.compose")
}

android {
    namespace = "com.majorkik.core.ui"
}

dependencies {
    api(libs.androidx.appcompat)

    api(platform(libs.androidx.compose.bom))
    api(libs.bundles.compose)
    api(libs.accompanist.insets)

    api(libs.lifecycle.viewmodel.compose)
}
