plugins { 
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose) 
}

kotlin { 
    androidTarget() 
}

android {
    namespace = "com.synapseplus.android"
    compileSdk = 34
    defaultConfig { 
        applicationId = "com.synapseplus"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0" 
    }
    buildFeatures { 
        compose = true 
    }
    composeOptions { 
        kotlinCompilerExtensionVersion = "1.5.14" 
    }
}

dependencies { 
    implementation(project(":shared"))
    implementation(platform("androidx.compose:compose-bom:2024.06.00"))
    implementation("androidx.activity:activity-compose:1.9.0") 
}
