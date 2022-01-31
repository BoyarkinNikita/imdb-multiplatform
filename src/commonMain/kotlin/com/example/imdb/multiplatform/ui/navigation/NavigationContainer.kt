package com.example.imdb.multiplatform.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value

@Composable
fun <C : Any> NavigationContainer(stateRouter: RouterState<C, @Composable () -> Unit>) {
    class Keys(var set: Set<Any>)

    fun Any.key(): String = "${this::class.simpleName}_${hashCode().toString(radix = 36)}"

    val holder = rememberSaveableStateHolder()

    val activeChild = stateRouter.activeChild

    val configurations = HashSet<String>()
    stateRouter.backStack.forEach { configurations += it.configuration.key() }
    configurations += activeChild.configuration.key()

    val keys = remember(holder) { Keys(configurations) }

    DisposableEffect(holder, configurations) {
        keys.set.forEach {
            if (it !in configurations) {
                holder.removeState(it)
            }
        }

        keys.set = configurations

        onDispose {}
    }

    holder.SaveableStateProvider(activeChild.configuration.key()) { activeChild.instance.invoke() }
}

@Composable
fun <C : Any> Value<RouterState<C, @Composable () -> Unit>>.subscribeAsState(): State<RouterState<C, @Composable () -> Unit>> {
    val stateRouter = remember(this) { mutableStateOf(value) }

    DisposableEffect(this) {
        val observer: (RouterState<C, @Composable () -> Unit>) -> Unit = { stateRouter.value = it }
        subscribe(observer)
        onDispose { unsubscribe(observer) }
    }

    return stateRouter
}