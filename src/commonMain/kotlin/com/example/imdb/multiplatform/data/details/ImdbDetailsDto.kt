package com.example.imdb.multiplatform.data.details

import com.example.imdb.multiplatform.data.ImdbDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImdbDetailsDto(
    @SerialName("Title")
    val title: String? = null,

    @SerialName("Year")
    val year: String? = null,

    @SerialName("Rated")
    val rated: String? = null,

    @SerialName("Released")
    val released: String? = null,

    @SerialName("Runtime")
    val runtime: String? = null,

    @SerialName("Genre")
    val genre: String? = null,

    @SerialName("Director")
    val director: String? = null,

    @SerialName("Writer")
    val writers: String? = null,

    @SerialName("Actors")
    val actors: String? = null,

    @SerialName("Plot")
    val plot: String? = null,

    @SerialName("Language")
    val language: String? = null,

    @SerialName("Country")
    val country: String? = null,

    @SerialName("Awards")
    val awards: String? = null,

    @SerialName("Poster")
    val posterUrl: String? = null,

    @SerialName("imdbRating")
    val rating: String? = null,

    @SerialName("imdbVotes")
    val votes: String? = null,

    @SerialName("imdbID")
    val imdbId: String? = null,

    @SerialName("Type")
    val type: String? = null,

    @SerialName("DVD")
    val dvd: String? = null,

    @SerialName("BoxOffice")
    val boxOffice: String? = null,

    @SerialName("Production")
    val production: String? = null,

    @SerialName("Website")
    val website: String? = null
) : ImdbDto()