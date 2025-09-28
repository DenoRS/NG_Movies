plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hiltAndroid)
    id("com.google.devtools.ksp")
}

//val tmdbApiKey: String = project.findProperty("TMDB_API_KEY") as? String
//    ?: error("TMDB_API_KEY is missing from local.properties")
//
//val tmdbAccessToken: String = project.findProperty("TMDB_ACCESS_TOKEN") as? String
//    ?: error("TMDB_ACCESS_TOKEN is missing from local.properties")


android {
    namespace = "ie.rubberduck.ngmovies"
    compileSdk = 36

    defaultConfig {
        applicationId = "ie.rubberduck.ngmovies"
        minSdk = 35
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

//        val tmdbApiKey: String = project.findProperty("TMDB_API_KEY") as String
//        val tmdbAccessToken: String = project.findProperty("TMDB_ACCESS_TOKEN") as String

//        buildConfigField("String", "TMDB_API_KEY", "\"$tmdbApiKey\"")
//        buildConfigField("String", "TMDB_ACCESS_TOKEN", "\"$tmdbAccessToken\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
//    kotlin {
//        jvmToolchain(17)
//    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.plugins.kotlin.compose.get().version.strictVersion
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":components"))
    implementation(project(":theming"))

    implementation(libs.javax.inject)

    // Coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    // Compose
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.navigation.compose)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.core.ktx)
    ksp(libs.hilt.compiler)

    // Room
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)

    // Moshi
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    ksp(libs.moshi.codegen)

    // Retrofit
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.retrofit.converter.gson)

    // Coroutines
    implementation(libs.coroutines)

    // Testing
    testImplementation(libs.coroutines.test)
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.params)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testImplementation(libs.mockk)
    testImplementation(libs.turbine)
}
