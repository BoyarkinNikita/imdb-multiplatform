package com.example.imdb.multiplatform.ui.base

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.example.imdb.multiplatform.di.inject
import com.example.imdb.multiplatform.di.provide
import com.example.imdb.multiplatform.helpers.ViewModelStore
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.lighthousegames.logging.KmLogging
import org.lighthousegames.logging.logging
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<Dest : BaseDestination<*>>(
    componentContext: ComponentContext,
    val destination: Dest
) : ComponentContext by componentContext, InstanceKeeper.Instance {
    protected val viewModelStore: ViewModelStore by inject()

    protected val log by lazy { logging(KmLogging.createTag(this::class.simpleName).first) }

    protected val scope: CoroutineScope = run {
        @OptIn(ExperimentalCoroutinesApi::class)
        val dispatcher = runCatching { Main }.getOrNull()
            ?: Dispatchers.Default.limitedParallelism(1)

        CoroutineScope(SupervisorJob(provide<Job>()) + dispatcher)
    }

    protected open val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        log.w(err = throwable) {}
        scope.launch { eventError.emit(throwable) }
    }

    val eventError = MutableStateFlow<Throwable?>(null)

    init {
        instanceKeeper.getOrCreate { this }
    }

    override fun onDestroy() {
        viewModelStore.remove(this)
        instanceKeeper.remove(this::class)
        runCatching { scope.run { if (isActive) cancel() } }
    }

    protected fun scopeLaunch(
        coroutineContext: CoroutineContext = exceptionHandler,
        action: suspend CoroutineScope.() -> Unit
    ): Job = scope.launch(coroutineContext) { action() }
}