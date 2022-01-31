package com.example.imdb.multiplatform.ui.global

import com.arkivanov.decompose.router.push
import com.example.imdb.multiplatform.ui.application.appViewModel
import com.example.imdb.multiplatform.ui.bottom.movies.MoviesViewModel
import com.example.imdb.multiplatform.ui.global.main.MainDestination
import com.example.imdb.multiplatform.ui.global.movieDetails.MovieDetailsDestination
import com.example.imdb.multiplatform.ui.global.splash.SplashViewModel

fun SplashViewModel.navigateToMain() {
    appViewModel.router.navigate { listOf(MainDestination()) }
}

fun MoviesViewModel.navigateToMovieDetails(imdbId: String) {
    appViewModel.router.push(MovieDetailsDestination(imdbId))
}