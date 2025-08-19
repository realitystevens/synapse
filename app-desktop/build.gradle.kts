plugins { 
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose) 
}

kotlin { 
    jvm("desktop") 
}

dependencies { 
    "desktopImplementation"(project(":shared")) 
}
