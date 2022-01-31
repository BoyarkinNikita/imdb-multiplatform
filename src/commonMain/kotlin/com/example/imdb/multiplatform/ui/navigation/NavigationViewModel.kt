package com.example.imdb.multiplatform.ui.navigation

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.Router
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.pop
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.ValueObserver
import com.example.imdb.multiplatform.ui.base.BaseDestination
import com.example.imdb.multiplatform.ui.base.BaseViewModel
import io.ktor.util.collections.ConcurrentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.reflect.KClass
import kotlin.reflect.cast

abstract class NavigationViewModel<ChildDest : BaseDestination<*>, ParentDest : BaseDestination<*>>(
    componentContext: ComponentContext,
    destination: ParentDest,
    routerDestinationKlass: KClass<ChildDest>,
    startDestination: ChildDest,
    private val parentViewModel: NavigationViewModel<ParentDest, *>?
) : BaseViewModel<ParentDest>(
    componentContext = componentContext,
    destination = destination
) {
    val router: Router<ChildDest, @Composable () -> Unit> = componentContext.router(
        initialConfiguration = { startDestination },
        handleBackButton = false,
        key = "router" + routerDestinationKlass.simpleName + this::class.simpleName,
        configurationClass = routerDestinationKlass,
        childFactory = { destination, componentContext ->
            routerDestinationKlass.cast(destination).createComposeFactory(
                componentContext = componentContext,
                viewModelStore = viewModelStore
            )
        }
    )

    private val childNavigationList = ConcurrentList<NavigationViewModel<*, ChildDest>>()

    private val mutableCanNavigateBack = MutableStateFlow(canGoBack())
    val canNavigateBack = mutableCanNavigateBack.asStateFlow()

    protected val onCanGoBackChanged: ValueObserver<RouterState<ChildDest, () -> Unit>> = {
        parentViewModel?.mutableCanNavigateBack?.value = canGoBack()
        mutableCanNavigateBack.value = canGoBack()
    }

    init {
        parentViewModel?.registerChildNavigation(this)
        router.state.subscribe(onCanGoBackChanged)
    }

    protected open fun handleBackPressed(): Boolean {
        val activeChild = router.state.value.activeChild

        childNavigationList.asReversed().forEach { childNavigation ->
            if (
                childNavigation.destination::class == activeChild.configuration::class &&
                childNavigation.handleBackPressed()
            ) return true
        }

        return if (router.state.value.backStack.isNotEmpty()) {
            router.pop()
            true
        } else false
    }

    protected open fun canGoBack(): Boolean {
        val activeChild = router.state.value.activeChild

        childNavigationList.asReversed().forEach { childNavigation ->
            if (
                childNavigation.destination::class == activeChild.configuration::class &&
                childNavigation.canGoBack()
            ) return true
        }

        return router.state.value.backStack.isNotEmpty()
    }

    private fun registerChildNavigation(childNavigation: NavigationViewModel<*, ChildDest>) {
        childNavigationList.add(childNavigation)
    }

    private fun unregisterChildNavigation(childNavigation: NavigationViewModel<*, ChildDest>) {
        childNavigationList.remove(childNavigation)
    }

    override fun onDestroy() {
        super.onDestroy()
        router.state.unsubscribe(onCanGoBackChanged)
        childNavigationList.clear()
        parentViewModel?.unregisterChildNavigation(this)
    }
}