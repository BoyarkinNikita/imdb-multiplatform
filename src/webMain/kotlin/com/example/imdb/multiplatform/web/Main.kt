package com.example.imdb.multiplatform.web

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.window.Window
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.example.imdb.multiplatform.di.webModule
import com.example.imdb.multiplatform.onApplicationCreated
import com.example.imdb.multiplatform.onApplicationDestroyed
import com.example.imdb.multiplatform.ui.application.ApplicationEntry
import com.example.imdb.multiplatform.ui.application.exitApplicationFlow
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onApplicationCreated {
        modules(webModule)
    }

    val lifecycle = LifecycleRegistry()

    onWasmReady {
        Window("Imdb") {
            LaunchedEffect(Unit) {
                exitApplicationFlow.collectLatest {
                    onApplicationDestroyed()
                    js("window.close();") as Unit
                }
            }

            ApplicationEntry(DefaultComponentContext(lifecycle))
        }
    }
}