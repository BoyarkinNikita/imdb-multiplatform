package com.example.imdb.multiplatform.desktop

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.example.imdb.multiplatform.di.desktopModule
import com.example.imdb.multiplatform.isDebug
import com.example.imdb.multiplatform.onApplicationCreated
import com.example.imdb.multiplatform.onApplicationDestroyed
import com.example.imdb.multiplatform.res.string
import com.example.imdb.multiplatform.ui.application.ApplicationEntry
import com.example.imdb.multiplatform.ui.application.exitApplicationFlow
import kotlinx.coroutines.flow.collectLatest
import org.koin.core.logger.PrintLogger

@OptIn(ExperimentalDecomposeApi::class)
fun main() {
    val lifecycle = LifecycleRegistry()

    onApplicationCreated {
        if (isDebug) logger(PrintLogger())
        modules(desktopModule)
    }

    application {
        val windowState = rememberWindowState()
        LifecycleController(lifecycle, windowState)

        val onExit = {
            onApplicationDestroyed()
            exitApplication()
        }

        LaunchedEffect(Unit) {
            exitApplicationFlow.collectLatest { onExit() }
        }

        Window(
            onCloseRequest = onExit,
            state = windowState,
            title = string.appName
        ) {
            ApplicationEntry(DefaultComponentContext(lifecycle))
        }
    }
}