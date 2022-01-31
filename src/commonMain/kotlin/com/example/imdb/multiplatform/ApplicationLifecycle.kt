package com.example.imdb.multiplatform

import com.example.imdb.multiplatform.di.commonModule
import com.example.imdb.multiplatform.di.networkModule
import com.example.imdb.multiplatform.di.provide
import com.example.imdb.multiplatform.di.repositoryModule
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger
import org.lighthousegames.logging.FixedLogLevel
import org.lighthousegames.logging.KmLogging
import org.lighthousegames.logging.PlatformLogger

inline fun onApplicationCreated(
    crossinline koinInit: KoinApplication.() -> Unit = {}
) {
    KmLogging.addLogger(PlatformLogger(FixedLogLevel { isDebug }))

    startKoin {
        logger(EmptyLogger())
        modules(commonModule, networkModule, repositoryModule)
        koinInit()
    }
}

fun onApplicationDestroyed() {
    provide<Job>().cancelChildren()
}