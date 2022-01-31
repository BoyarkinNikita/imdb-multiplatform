package com.example.imdb.multiplatform.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
abstract class ImdbDto {
    @SerialName("Response")
    val response: String? = null

    @SerialName("Error")
    val error: String? = null
}