package com.example.imdb.multiplatform.ui.global.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.example.imdb.multiplatform.ui.application.appViewModel
import com.example.imdb.multiplatform.ui.navigation.NavigationContainer
import com.example.imdb.multiplatform.ui.navigation.subscribeAsState

@Composable
fun MainScreen(viewModel: MainViewModel) {
    LaunchedEffect(Unit) {
        appViewModel.apply {
            showToolbar.value = true
            bottomBarNavigation.value = viewModel
        }
    }

    val stateRouter by viewModel.router.state.subscribeAsState()
    NavigationContainer(stateRouter)
}