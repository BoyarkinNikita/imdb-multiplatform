package com.example.imdb.multiplatform.ui.global.movieDetails

import com.arkivanov.decompose.ComponentContext
import com.example.imdb.multiplatform.data.details.ImdbDetailsItem
import com.example.imdb.multiplatform.di.provide
import com.example.imdb.multiplatform.repository.imdb.ImdbRepository
import com.example.imdb.multiplatform.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

class MovieDetailsViewModel(
    componentContext: ComponentContext,
    destination: MovieDetailsDestination
) : BaseViewModel<MovieDetailsDestination>(componentContext, destination) {
    val details = MutableStateFlow<ImdbDetailsItem?>(null)
    val loading = MutableStateFlow(false)

    init {
        scopeLaunch {
            loading.value = true

            val result = withContext(Default) {
                runCatching { provide<ImdbRepository>().details(destination.imdbId) }
            }

            loading.value = false

            result.onSuccess { details.value = it }
                .onFailure { eventError.value = it }
        }
    }
}