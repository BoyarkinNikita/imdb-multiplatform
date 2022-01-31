package com.example.imdb.multiplatform.data.search

import com.example.imdb.multiplatform.data.ImdbDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImdbShortDto(
    @SerialName("Title")
    val title: String? = null,

    @SerialName("Year")
    val year: String? = null,

    @SerialName("Type")
    val type: String? = null,

    @SerialName("Poster")
    val posterUrl: String? = null,

    @SerialName("imdbID")
    val imdbId: String? = null
) : ImdbDto()