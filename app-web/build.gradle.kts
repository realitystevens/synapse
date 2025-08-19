plugins { 
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose) 
}

kotlin { 
    js(IR) { 
        browser() 
    } 
}

dependencies { 
    "jsMainImplementation"(project(":shared")) 
}
