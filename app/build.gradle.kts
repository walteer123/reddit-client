plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.walter.reddit_client"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding {
        isEnabled = true
    }
}

dependencies {
    implementation(Libs.coreKtx)
    implementation(Libs.appCompat)
    implementation(Libs.material)
    implementation(Libs.constraintLayout)
    implementation(Libs.retrofit)
    implementation(Libs.koinAndroid)
    implementation(Libs.navigationFragment)
    implementation(Libs.navigationUi)
    implementation(Libs.pagingRuntime)
    implementation(Libs.okHttp3LoggingInterceptor)

    implementation("io.coil-kt:coil:1.4.0")
    implementation("com.google.code.gson:gson:2.8.9")
    implementation("com.squareup.retrofit2:converter-gson:2.8.1")

    implementation("androidx.room:room-runtime:2.4.1")
    implementation("androidx.room:room-ktx:2.4.1")
    implementation("androidx.room:room-paging:2.4.1")

    kapt("androidx.room:room-compiler:2.4.1")

    testImplementation(Libs.jUnit)
    testImplementation(Libs.koinTest)
    testImplementation(Libs.mockitoCore)
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
    testImplementation("app.cash.turbine:turbine:0.7.0")

    androidTestImplementation(Libs.testJUnit)
    androidTestImplementation(Libs.espressoCore)
    androidTestImplementation(Libs.testRunner)
    androidTestImplementation(Libs.testRules)
}