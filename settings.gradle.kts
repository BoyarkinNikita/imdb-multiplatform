pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev/")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://jitpack.io")

        mavenLocal()
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}

rootProject.name = "imdb-multiplatform"

//include(":androidLauncher")
