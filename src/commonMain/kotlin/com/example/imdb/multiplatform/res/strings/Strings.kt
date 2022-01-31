package com.example.imdb.multiplatform.res.strings

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.intl.Locale
import kotlin.LazyThreadSafetyMode.PUBLICATION

open class Strings protected constructor() {
    open val appName = "Imdb"

    open val errorMessageUnknown = "Unknown error."

    open val mainMenuMovies = "Movies"
    open val mainMenuNews = "News"

    open val toolbarMenuSearch = "Search"

    open val imdbTypeMovie = "Movie"
    open val imdbTypeSeries = "Series"

    open val movieDetailsDirector = "Director: "
    open val movieDetailsWriters = "Writers: "
    open val movieDetailsActors = "Actors: "

    open fun movieDetailsRating(rating: String) = "$rating/10"

    companion object {
        val instance by lazy(PUBLICATION) { Strings() }

        val strings: Strings
            @Composable
            get() = when (Locale.current.language.lowercase()) {
                "rus", "ru" -> StringsRu.instance
                "eng", "en" -> StringsEn.instance
                else -> instance
            }
    }
}