pluginManagement {
    repositories { 
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories { 
        google()
        mavenCentral()
    }
}

rootProject.name = "synapse_kmp"
include(":shared", ":app-android", ":app-desktop", ":app-web", ":app-ios")
