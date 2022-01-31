package com.example.imdb.multiplatform.ui.global.splash

import com.arkivanov.decompose.ComponentContext
import com.example.imdb.multiplatform.ui.base.BaseViewModel
import com.example.imdb.multiplatform.ui.global.navigateToMain
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock

class SplashViewModel(
    componentContext: ComponentContext,
    destination: SplashDestination
) : BaseViewModel<SplashDestination>(componentContext, destination) {
    init {
        scopeLaunch {
            val beforeTime = Clock.System.now()

            withContext(Default) { initializeOnBackground() }

            val afterTime = Clock.System.now()

            val initDurationMs = (afterTime - beforeTime).inWholeMilliseconds

            log.i { "Initialize time {${initDurationMs}ms}" }

            delay(MIN_DELAY_MILLIS - initDurationMs)

            navigateToMain()
        }
    }

    private fun initializeOnBackground() {

    }
}

private const val MIN_DELAY_MILLIS = 2_000