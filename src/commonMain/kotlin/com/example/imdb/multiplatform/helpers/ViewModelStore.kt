package com.example.imdb.multiplatform.helpers

import com.arkivanov.decompose.ComponentContext
import com.example.imdb.multiplatform.ui.base.BaseViewModel
import io.ktor.util.collections.ConcurrentList
import kotlin.reflect.KClass
import kotlin.reflect.safeCast

class ViewModelStore {
    private val viewModels = ConcurrentList<BaseViewModel<*>>()

    inline fun <reified T : BaseViewModel<*>> getOrCreate(
        componentContext: ComponentContext,
        noinline createViewModel: () -> T
    ): T = getOrCreate(T::class, componentContext, createViewModel)

    fun <T : BaseViewModel<*>> getOrCreate(
        viewModelKlass: KClass<T>,
        componentContext: ComponentContext,
        createViewModel: () -> T
    ): T {
        val viewModel = viewModels.find {
            it.backPressedHandler === componentContext.backPressedHandler &&
                    it.instanceKeeper === componentContext.instanceKeeper &&
                    it.lifecycle === componentContext.lifecycle
        }?.let { viewModelKlass.safeCast(it) }

        return viewModel ?: (createViewModel().also { viewModels.add(it) })
    }

    fun remove(viewModel: BaseViewModel<*>): Boolean =
        viewModels.remove(viewModel)
}