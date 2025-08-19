plugins { 
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    iosArm64()
    iosX64() 
    iosSimulatorArm64()
    
    sourceSets {
        val iosMain by creating {
            dependencies {
                implementation(project(":shared"))
            }
        }
        val iosArm64Main by getting { dependsOn(iosMain) }
        val iosX64Main by getting { dependsOn(iosMain) }
        val iosSimulatorArm64Main by getting { dependsOn(iosMain) }
    }
}
