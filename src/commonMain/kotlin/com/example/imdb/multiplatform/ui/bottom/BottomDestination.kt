package com.example.imdb.multiplatform.ui.bottom

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.example.imdb.multiplatform.helpers.ViewModelStore
import com.example.imdb.multiplatform.ui.base.BaseDestination
import com.example.imdb.multiplatform.ui.global.main.MainViewModel

abstract class BottomDestination : BaseDestination<MainViewModel>() {
    // Must be overriden for android build.
    override fun createComposeFactory(
        componentContext: ComponentContext,
        viewModelStore: ViewModelStore
    ): @Composable () -> Unit = {}
}