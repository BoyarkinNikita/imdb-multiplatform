package com.example.imdb.multiplatform.data.details

import com.example.imdb.multiplatform.data.ImdbContentType
import kotlinx.serialization.Serializable

@Serializable
data class ImdbDetailsItem(
    val imdbId: String,
    val title: String,
    val type: ImdbContentType,
    val description: String,
    val year: String?,
    val plot: String?,
    val posterUrl: String?,
    val rating: String?,
    val votes: String?,
    val director: String?,
    val writers: String?,
    val actors: String?,
    val boxOffice: String?,
    val website: String?
) {
    companion object {
        fun fromDtoOrThrow(dto: ImdbDetailsDto): ImdbDetailsItem = with(dto) {
            if (imdbId == null) throw RuntimeException("Nullable IMDB identifier.")
            val title = title ?: "[ BLANK ]"
            val type = if (type == "series") ImdbContentType.SERIES else ImdbContentType.MOVIE
            val votes = votes?.replace("[^0-9]".toRegex(), "")
            val description = arrayOf(rated, runtime, genre).filterNotNull()
                .joinToString(separator = " | ")

            return ImdbDetailsItem(
                imdbId = imdbId, title = title, type = type, description = description,
                year = year, plot = plot, posterUrl = posterUrl, rating = rating,
                votes = votes, director = director, writers = writers,
                actors = actors, boxOffice = boxOffice, website = website
            )
        }
    }
}