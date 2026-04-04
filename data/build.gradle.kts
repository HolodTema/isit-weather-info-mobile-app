plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.terabyte.data"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            buildConfigField(
                "String",
                "BASE_TIME_API_URL",
                "\"https://api.sunrise-sunset.org/json\""
            )
            buildConfigField(
                "String",
                "BASE_TEMPERATURE_API_URL",
                "\"https://archive-api.open-meteo.com\""
            )
        }
        release {
            buildConfigField(
                "String",
                "BASE_TIME_API_URL",
                "\"https://api.sunrise-sunset.org/json\""
            )
            buildConfigField(
                "String",
                "BASE_TEMPERATURE_API_URL",
                "\"https://archive-api.open-meteo.com\""
            )

            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    //Retrofit + moshi json converter
    //I made it api() because of Dagger from :app module has to access Retrofit, Moshi
    api(libs.retrofit)
    api(libs.retrofit.converter.moshi)

    //Http interceptor for logging requests
    api(libs.okhttp.logging.interceptor)

    //Moshi JSON lib to work with Retrofit
    api(libs.moshi)
    api(libs.moshi.kotlin)

    //dagger with codegen
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(project(":domain"))
}