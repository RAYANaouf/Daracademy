plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.daracademy"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.daracademy"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("com.google.firebase:firebase-auth-ktx:22.3.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")


    /*******************  UI **************************/

    //view model
    // https://mvnrepository.com/artifact/androidx.lifecycle/lifecycle-viewmodel-compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    // https://mvnrepository.com/artifact/androidx.compose.ui/ui-text-google-fonts
    implementation("androidx.compose.ui:ui-text-google-fonts:1.5.4")

    //navigation
    // https://mvnrepository.com/artifact/androidx.navigation/navigation-compose
    implementation("androidx.navigation:navigation-compose:2.6.0")

    //coil
    // https://mvnrepository.com/artifact/io.coil-kt/coil-compose
    implementation("io.coil-kt:coil-compose:2.4.0")


    //swipe refresh
    // https://mvnrepository.com/artifact/com.google.accompanist/accompanist-swiperefresh
    implementation("com.google.accompanist:accompanist-swiperefresh:0.32.0")

    //lottie
    //pretty animation "json format"
    implementation("com.airbnb.android:lottie-compose:6.1.0")


    //dataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // https://mvnrepository.com/artifact/me.saket.swipe/swipe
    implementation("me.saket.swipe:swipe:1.2.0")



    /****************************  firebase   ****************************/
    implementation(platform("com.google.firebase:firebase-bom:32.6.0"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-storage")



}