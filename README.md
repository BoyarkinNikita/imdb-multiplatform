### IMDB demo multiplatform application

A simple application with the possibility of searching through movies, series and news.

Public API:
- **Movie/Series** - [OMDB (IMDB) API](https://www.omdbapi.com/ "OMDB API")

Stack:
- **Architecture** - MVVM
- **Libraries** - Compose Multiplatform, Skiko, Coroutines, Ktor, Koin, Decompose Navigation,  Kotlinx Serialization.

### Project

Project structure:
- Build script: `build.gradle.kts`
- Common code: `src/commonMain`
- Target platform code: `src/androidMain`, `src/desktopMain`, `src/webMain`
- Common tests: `src/commonTest`
- Target platform tests: `src/androidTest`, `src/desktopTest`
- Versions file: `buildSrc/src/main/kotlin/Ver.kt`

How to build:
- Desktop target: `./gradlew run` (requires Ver.jvmJava to launch, better 11 or 17)
- Android target: `./gradlew assembleDebug` (requires Ver.androidJava to launch, better 8 or 11)
- Web target: `./gradlew webBrowserRun` (⚠️ don't work, we have to wait new Compose Multiplatform + Skiko versions ⚠️)

Run tests:
- Desktop tests (+ common tests): `./gradlew desktopTest`
- Android tests (+ common tests): `./gradlew testDebugUnitTest`

To run tests in Android Studio / IntelliJ you have to create new configuration (`Edit Configurations... -> Add New Configuration -> Gradle`) with parameters:
- Gradle project: `imdb-multiplatform`
- Tasks: `gradleTaskName` (e.g. `desktopTest`)
- Arguments: `--tests *`

### Tasks

DONE:
- Movies list screen.
- Movie details screen.
- Imdb API and images loading.
- Disk Caching for Imdb API and images.
- Movies list Pagination.
- Unit tests for cache.

TODO:
- Movie search.
- Preview image after tap on it.
- Movie news list screen.
- Movie news details screen.
- News API and caching.
- UI tests.
- More unit tests.

