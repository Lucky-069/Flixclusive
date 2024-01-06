plugins {
    alias(libs.plugins.flixclusive.library)
    alias(libs.plugins.flixclusive.hilt)
}

android {
    namespace = "com.flixclusive.core.network"
}

dependencies {
    api(libs.okhttp)
    api(libs.okhttp.dnsoverhttps)
    api(libs.retrofit)

    implementation(libs.retrofit.gson)
    implementation(projects.core.datastore)
    implementation(projects.core.util)
    implementation(projects.model.datastore)
    implementation(projects.model.tmdb)
    implementation(libs.conscrypt)
}