pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://www.jitpack.io")
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://www.jitpack.io")
    }
}

rootProject.name = "SIKComposeHub"
include(":app")
include(":SIKComposeBase")
include(":SIKComposeUI")
