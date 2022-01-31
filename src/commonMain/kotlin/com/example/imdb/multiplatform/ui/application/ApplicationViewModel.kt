package com.example.imdb.multiplatform.ui.application

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.example.imdb.multiplatform.helpers.ViewModelStore
import com.example.imdb.multiplatform.ui.base.BaseDestination
import com.example.imdb.multiplatform.ui.global.GlobalDestination
import com.example.imdb.multiplatform.ui.global.splash.SplashDestination
import com.example.imdb.multiplatform.ui.navigation.BottomNavigation
import com.example.imdb.multiplatform.ui.navigation.NavigationViewModel
import kotlinx.atomicfu.AtomicRef
import kotlinx.atomicfu.atomic
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

inline val appViewModel: ApplicationViewModel
    get() = ApplicationViewModel.instance.value!!

val exitApplicationFlow by lazy {
    MutableSharedFlow<Unit>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
}

class ApplicationViewModel private constructor(
    componentContext: ComponentContext
) : NavigationViewModel<GlobalDestination, RootDestination>(
    componentContext = componentContext,
    destination = RootDestination(),
    routerDestinationKlass = GlobalDestination::class,
    startDestination = SplashDestination(),
    parentViewModel = null
) {

    val showToolbar = MutableStateFlow<Boolean>(false)
    val toolbarTitle = MutableStateFlow<String?>(null)

    val bottomBarNavigation = MutableStateFlow<BottomNavigation?>(null)

    init {
        backPressedHandler.register(::onBackPressed)
    }

    fun exitApplication() {
        exitApplicationFlow.run { if (!tryEmit(Unit)) scopeLaunch { emit(Unit) } }
    }

    fun navigateBack(): Boolean = onBackPressed()

    private fun onBackPressed(): Boolean {
        if (!handleBackPressed()) exitApplication()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        backPressedHandler.unregister(::onBackPressed)
    }

    companion object {
        val instance: AtomicRef<ApplicationViewModel?> = atomic(null)

        fun initAppViewModel(componentContext: ComponentContext) {
            instance.compareAndSet(null, ApplicationViewModel(componentContext))
        }
    }
}

@Parcelize
class RootDestination : BaseDestination<Unit>(), Parcelable {
    override fun createComposeFactory(
        componentContext: ComponentContext,
        viewModelStore: ViewModelStore
    ): @Composable () -> Unit = @Composable {}
}