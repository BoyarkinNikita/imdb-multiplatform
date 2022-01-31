package com.example.imdb.multiplatform.ui.global

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.example.imdb.multiplatform.helpers.ViewModelStore
import com.example.imdb.multiplatform.ui.application.ApplicationViewModel
import com.example.imdb.multiplatform.ui.base.BaseDestination

abstract class GlobalDestination : BaseDestination<ApplicationViewModel>() {
    // Must be overriden for android build.
    override fun createComposeFactory(
        componentContext: ComponentContext,
        viewModelStore: ViewModelStore
    ): @Composable () -> Unit = {}
}