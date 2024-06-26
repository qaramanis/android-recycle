plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.androidrecycle"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.androidrecycle"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures{
        dataBinding = true
        viewBinding = true
        compose = true
    }
    composeOptions{
        kotlinCompilerExtensionVersion = "1.5.13"
    }
}

dependencies {
    implementation(libs.okhttp)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.viewpager2)
    implementation(libs.glide)
    implementation(libs.activity.v160alpha05)
    implementation(libs.play.services.maps)
    implementation(libs.appcompat)
    implementation(libs.androidx.appcompat.v100)
    implementation(libs.material3)
    implementation(libs.material)
    implementation(libs.androidx.material3.adaptive.navigation.suite)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.legacy.support.v4)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)
    implementation(libs.rendering)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}