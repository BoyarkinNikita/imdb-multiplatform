import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension

buildscript {
    repositories {
        initRepositories()
    }
}

allprojects {
    repositories {
        initRepositories()
    }
}

plugins {
    kotlin("multiplatform") version Ver.kotlin
    kotlin("plugin.serialization") version Ver.kotlin
    id("com.android.application") version Ver.gradle
    id("org.jetbrains.compose") version Ver.compose
}

version = Ver.versionName

kotlin {
    android(name = "android") {
        java.run {
            sourceCompatibility = Ver.androidJava
            targetCompatibility = Ver.androidJava
        }

        compilations.all {
            kotlinOptions.jvmTarget = Ver.androidJava.toString()
            languageSettings.optIn("kotlin.RequiresOptIn")
        }
    }

    jvm(name = "desktop") {
        java.run {
            sourceCompatibility = Ver.jvmJava
            targetCompatibility = Ver.jvmJava
        }

        compilations.all {
            kotlinOptions.jvmTarget = Ver.jvmJava.toString()
            languageSettings.optIn("kotlin.RequiresOptIn")
        }
    }

    js(name = "web", compiler = IR) {
        browser()
        binaries.executable()
    }

    sourceSets {
        all {
            languageSettings.optIn("kotlin.RequiresOptIn")
        }

        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib-common:${Ver.kotlin}")
                implementation("org.jetbrains.kotlin:kotlin-reflect:${Ver.kotlin}")

                api(compose.runtime)
                api(compose.foundation)
                api(compose.ui)
                api(compose.material)
                api("org.jetbrains.compose.material:material-icons-core:${Ver.compose}")

                //api(compose.uiTooling)
                //@kotlin.OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                //api(compose.material3)
                //api(compose.materialIconsExtended)

                api("org.jetbrains.skiko:skiko:${Ver.skiko}")

                api("com.goncalossilva:murmurhash:${Ver.murHash}")

                api("org.jetbrains.kotlinx:kotlinx-datetime:${Ver.dateTime}")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Ver.coroutines}")
                api("org.jetbrains.kotlinx:kotlinx-serialization-json:${Ver.serialization}")
                api("org.jetbrains.kotlinx:kotlinx-serialization-cbor:${Ver.serialization}")
                api("org.jetbrains.kotlinx:atomicfu:${Ver.atomic}")

                api("com.arkivanov.decompose:decompose:${Ver.decompose}")

                api("io.ktor:ktor-client-core:${Ver.ktor}")
                api("io.ktor:ktor-client-content-negotiation:${Ver.ktor}")
                api("io.ktor:ktor-serialization-kotlinx-json:${Ver.ktor}")
                api("io.ktor:ktor-client-json:${Ver.ktor}")

                api("io.insert-koin:koin-core:${Ver.koin}")

                api("org.lighthousegames:logging:${Ver.logger}")
                api("com.russhwolf:multiplatform-settings:${Ver.settings}")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test-common:${Ver.kotlin}")
                implementation("org.jetbrains.kotlin:kotlin-test-annotations-common:${Ver.kotlin}")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Ver.coroutines}")
            }
        }

        val desktopMain by getting {
            dependsOn(commonMain)

            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Ver.kotlin}")

                implementation("org.jetbrains.skiko:skiko-jvm:${Ver.skikoJvm}")
                implementation("org.jetbrains.skiko:skiko-jvm-runtime-linux-x64:${Ver.skikoJvm}")
                implementation("org.jetbrains.skiko:skiko-jvm-runtime-windows-x64:${Ver.skikoJvm}")

                implementation(compose.desktop.common)
                implementation(compose.desktop.linux_x64)
                implementation(compose.desktop.linux_arm64)
                implementation(compose.desktop.windows_x64)

                implementation("io.ktor:ktor-client-java:${Ver.ktor}")

                implementation("com.arkivanov.decompose:decompose-jvm:${Ver.decompose}")
                implementation("com.arkivanov.decompose:extensions-compose-jetbrains:${Ver.decompose}")
            }
        }

        val desktopTest by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test-junit:${Ver.kotlin}")
            }
        }

        val webMain by getting {
            dependsOn(commonMain)

            dependencies {
                implementation("org.jetbrains.skiko:skiko-js:${Ver.skiko}")
                implementation("org.jetbrains.skiko:skiko-js-wasm-runtime:${Ver.skiko}")

                implementation("io.ktor:ktor-client-js:${Ver.ktor}")
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)

            apply(plugin = "org.jetbrains.kotlin.kapt")
            apply(plugin = "kotlin-parcelize")

            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Ver.kotlin}")

                implementation("io.insert-koin:koin-android:${Ver.koin}")

                implementation("com.arkivanov.decompose:extensions-compose-jetpack:${Ver.decompose}")
                implementation("com.arkivanov.decompose:extensions-android:${Ver.decompose}")

                implementation("androidx.multidex:multidex:${Ver.multidex}")

                implementation("com.google.android.material:material:${Ver.material}")

                implementation("io.ktor:ktor-client-android:${Ver.ktor}")

                implementation("com.jakewharton:disklrucache:${Ver.diskCache}")

                implementation("androidx.core:core-ktx:${Ver.uiCore}")
                implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Ver.lifecycle}")
                implementation("androidx.lifecycle:lifecycle-common-java8:${Ver.lifecycle}")
                implementation("androidx.activity:activity:${Ver.activity}")
                implementation("androidx.activity:activity-compose:${Ver.activity}")
            }
        }

        val androidTest by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test-junit:${Ver.kotlin}")
            }
        }
    }
}

/** Android **/

android {
    compileSdk = Ver.compileSdk

    ndkVersion = Ver.ndk

    defaultConfig {
        applicationId = "com.example.imdb.multiplatform.android"

        minSdk = Ver.minSdk
        targetSdk = Ver.targetSdk

        ndkVersion = Ver.ndk

        versionCode = Ver.versionCode
        versionName = Ver.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        multiDexEnabled = true
    }

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    java.run {
        sourceCompatibility = Ver.androidJava
        targetCompatibility = Ver.androidJava
    }

    compileOptions {
        sourceCompatibility = Ver.androidJava
        targetCompatibility = Ver.androidJava
    }

    kotlin {
        compileOptions.run {
            sourceCompatibility = Ver.androidJava
            targetCompatibility = Ver.androidJava
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    lint {
        htmlReport = false
        xmlReport = false
        textReport = true
    }
}

/** Desktop **/

compose.desktop {
    application {
        mainClass = "com.example.imdb.multiplatform.desktop.MainKt"

        java.run {
            sourceCompatibility = Ver.jvmJava
            targetCompatibility = Ver.jvmJava
        }

        nativeDistributions {
            targetFormats(TargetFormat.Exe, TargetFormat.Deb, TargetFormat.Rpm)

            packageName = "com.example.imdb.multiplatform.desktop"
            packageVersion = Ver.versionName
        }
    }
}

kotlin {
    targets.withType<KotlinNativeTarget> {
        binaries.all {
            // TODO: the current compose binary surprises LLVM, so disable checks for now.
            freeCompilerArgs = freeCompilerArgs + "-Xdisable-phases=VerifyBitcode"
        }
    }
}

/** Web **/

val skikoWasm by configurations.creating

dependencies {
    skikoWasm("org.jetbrains.skiko:skiko-js-wasm-runtime:${Ver.skiko}")
}

val unzipTask = tasks.register("unzipWasm", Copy::class) {
    destinationDir = file("$buildDir/processedResources/web/main")
    from(skikoWasm.map { zipTree(it) })
}

tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile>().configureEach {
    dependsOn(unzipTask)
}

// a temporary workaround for a bug in jsRun invocation - see https://youtrack.jetbrains.com/issue/KT-48273
afterEvaluate {
    rootProject.extensions.configure<NodeJsRootExtension> {
        versions.webpackDevServer.version = "4.0.0"
        versions.webpackCli.version = "4.9.0"
        nodeVersion = "16.0.0"
    }
}

// TODO: remove when https://youtrack.jetbrains.com/issue/KT-50778 fixed
project.tasks.withType(org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile::class.java).configureEach {
    kotlinOptions.freeCompilerArgs += listOf("-Xir-dce-runtime-diagnostic=log")
}