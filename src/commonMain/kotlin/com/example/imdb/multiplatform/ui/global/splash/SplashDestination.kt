package com.example.imdb.multiplatform.ui.global.splash

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.example.imdb.multiplatform.helpers.ViewModelStore
import com.example.imdb.multiplatform.ui.global.GlobalDestination

@Parcelize
class SplashDestination : GlobalDestination(), Parcelable {
    override fun createComposeFactory(
        componentContext: ComponentContext,
        viewModelStore: ViewModelStore
    ): @Composable () -> Unit = {
        val viewModel = viewModelStore.getOrCreate(componentContext) {
            SplashViewModel(componentContext, this)
        }

        SplashScreen(viewModel)
    }
}