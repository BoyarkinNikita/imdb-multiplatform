package com.example.imdb.multiplatform.ui.bottom.news

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.imdb.multiplatform.res.strings.Strings.Companion.strings
import com.example.imdb.multiplatform.ui.application.appViewModel

@Composable
fun NewsScreen(viewModel: NewsViewModel) {
    val title = strings.mainMenuNews

    LaunchedEffect(Unit) {
        appViewModel.apply {
            showToolbar.value = true
            toolbarTitle.value = title
        }
    }
}