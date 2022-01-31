package com.example.imdb.multiplatform.data.search

import com.example.imdb.multiplatform.data.ImdbDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImdbSearchResultDto(
    @SerialName("Search")
    val search: List<ImdbShortDto>? = null,

    @SerialName("totalResults")
    val total: String? = null
) : ImdbDto()