package com.example.imdb.multiplatform.ui.bottom.news

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.example.imdb.multiplatform.helpers.ViewModelStore
import com.example.imdb.multiplatform.ui.bottom.BottomDestination

@Parcelize
class NewsDestination : BottomDestination(), Parcelable {
    override fun createComposeFactory(
        componentContext: ComponentContext,
        viewModelStore: ViewModelStore
    ): @Composable () -> Unit = {
        val viewModel = viewModelStore.getOrCreate(componentContext) {
            NewsViewModel(componentContext, this)
        }

        NewsScreen(viewModel)
    }
}