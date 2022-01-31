package com.example.imdb.multiplatform.ui.bottom

import com.arkivanov.decompose.router.bringToFront
import com.example.imdb.multiplatform.ui.bottom.movies.MoviesDestination
import com.example.imdb.multiplatform.ui.bottom.news.NewsDestination
import com.example.imdb.multiplatform.ui.global.main.MainViewModel

fun MainViewModel.navigateToMovies() {
    router.bringToFront(MoviesDestination())
}

fun MainViewModel.navigateToNews() {
    router.bringToFront(NewsDestination())
}