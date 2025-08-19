plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.serialization)
    alias(libs.plugins.sqldelight)
}

kotlin {
    androidTarget()
    iosArm64()
    iosX64()
    iosSimulatorArm64()
    jvm("desktop")
    js(IR) { browser() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(libs.ktor.core)
                implementation(libs.ktor.content.neg)
                implementation(libs.ktor.json)
                implementation(libs.coroutines.core)
                implementation(libs.serialization.json)
                implementation(libs.koin.core)
                implementation(libs.napier)
                implementation(libs.sqldelight.runtime)
                implementation(libs.voyager.navigator)
                implementation(libs.voyager.transitions)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.okhttp)
                implementation(libs.sqldelight.android)
                implementation(libs.onesignal)
                implementation(libs.revenuecat)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(libs.ktor.darwin)
                implementation(libs.sqldelight.native)
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation(libs.ktor.java)
                implementation(libs.sqldelight.sqlite)
            }
        }
        val jsMain by getting {
            dependencies { 
                implementation(libs.ktor.js) 
            }
        }
    }
}

sqldelight {
    databases {
        create("SynapseDb") {
            packageName.set("com.synapseplus.db")
            schemaOutputDirectory.set(file("$projectDir/src/commonMain/sqldelight/schema"))
        }
    }
}

android { 
    namespace = "com.synapseplus.shared"
    compileSdk = 34
    defaultConfig { minSdk = 24 }
}
