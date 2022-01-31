package com.example.imdb.multiplatform.ui.bottom.movies

import com.arkivanov.decompose.ComponentContext
import com.example.imdb.multiplatform.data.search.ImdbShortItem
import com.example.imdb.multiplatform.di.provide
import com.example.imdb.multiplatform.repository.imdb.ImdbRepository
import com.example.imdb.multiplatform.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

class MoviesViewModel(
    componentContext: ComponentContext,
    destination: MoviesDestination
) : BaseViewModel<MoviesDestination>(componentContext, destination) {
    private var lastQuery = "Movie"
    private var offset: UInt = 0u
    private var page: UInt = 1u

    val movies = MutableStateFlow(listOf<ImdbShortItem>())
    val loading = MutableStateFlow(false)

    var isLimitReached: Boolean = false

    init {
        search(lastQuery)
    }

    fun search(query: String) {
        loading.value = false
        movies.value = listOf()

        offset = 0u
        page = 1u
        isLimitReached = false
        lastQuery = query

        loadMovies()
    }

    fun loadMovies() {
        if (isLimitReached) return

        scopeLaunch {
            loading.value = true

            val result = withContext(Default) {
                runCatching { provide<ImdbRepository>().search(lastQuery, page) }
            }

            loading.value = false

            result.onSuccess { searchItem ->
                isLimitReached = offset + searchItem.items.size.toUInt() >= searchItem.total
                offset += searchItem.items.size.toUInt()
                page++

                movies.value = movies.value.toMutableList()
                    .apply { addAll(searchItem.items) }
                    .distinctBy { it.imdbId }
            }.onFailure { eventError.value = it }
        }
    }
}