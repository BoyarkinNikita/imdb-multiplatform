package com.example.imdb.multiplatform.data.search

import com.example.imdb.multiplatform.data.ImdbContentType
import kotlinx.serialization.Serializable

@Serializable
data class ImdbShortItem(
    val imdbId: String,
    val title: String,
    val type: ImdbContentType,
    val year: String?,
    val posterUrl: String?
) {

    companion object {
        fun fromDtoOrThrow(dto: ImdbShortDto): ImdbShortItem = with(dto) {
            if (imdbId == null) throw RuntimeException("Nullable IMDB identifier.")
            val title = title ?: "[ BLANK ]"
            val type = if (type == "series") ImdbContentType.SERIES else ImdbContentType.MOVIE
            return ImdbShortItem(
                imdbId = imdbId, title = title, type = type,
                year = year, posterUrl = posterUrl
            )
        }
    }
}