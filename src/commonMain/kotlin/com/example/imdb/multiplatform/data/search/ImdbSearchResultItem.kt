package com.example.imdb.multiplatform.data.search

import kotlinx.serialization.Serializable

@Serializable
data class ImdbSearchResultItem(
    val items: List<ImdbShortItem>,
    val total: UInt
) {
    companion object {
        fun fromDtoOrThrow(dto: ImdbSearchResultDto): ImdbSearchResultItem = with(dto) {
            val total = total?.toUIntOrNull() ?: throw RuntimeException("Wrong total.")
            val items = search?.map { ImdbShortItem.fromDtoOrThrow(it) } ?: listOf()
            ImdbSearchResultItem(items = items, total = total)
        }
    }
}