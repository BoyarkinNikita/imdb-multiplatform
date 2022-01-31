package com.example.imdb.multiplatform.api.imdb

import com.example.imdb.multiplatform.data.ImdbDto
import com.example.imdb.multiplatform.data.details.ImdbDetailsDto
import com.example.imdb.multiplatform.data.details.ImdbDetailsItem
import com.example.imdb.multiplatform.data.search.ImdbSearchResultDto
import com.example.imdb.multiplatform.data.search.ImdbSearchResultItem
import com.example.imdb.multiplatform.di.provide
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.utils.io.errors.IOException

class ImdbApi {
    @Throws(IOException::class, RuntimeException::class)
    suspend fun details(
        imdbId: String,
        plot: String = "full",
        key: String = imdbKeys.random()
    ): ImdbDetailsItem = provide<HttpClient>().get(IMDB_API_URL) {
        method = HttpMethod.Get
        contentType(Json)
        parameter("apikey", key)
        parameter("i", imdbId)
        parameter("plot", plot)
    }.body<ImdbDetailsDto>()
        .checkResult()
        .let { ImdbDetailsItem.fromDtoOrThrow(it) }

    @Throws(IOException::class, RuntimeException::class)
    suspend fun search(
        query: String,
        page: UInt,
        key: String = imdbKeys.random()
    ): ImdbSearchResultItem = provide<HttpClient>().get(IMDB_API_URL) {
        method = HttpMethod.Get
        contentType(Json)
        parameter("apikey", key)
        parameter("s", query)
        parameter("page", page)
    }.body<ImdbSearchResultDto>()
        .checkResult()
        .let { ImdbSearchResultItem.fromDtoOrThrow(it) }

    private fun <T : ImdbDto> T.checkResult(): T = apply {
        if (response != "True") throw RuntimeException(error ?: "Unknown error!")
    }

    companion object {
        private const val IMDB_API_URL = "https://www.omdbapi.com/"
        private val imdbKeys = arrayOf("509147ac", "b1be0fb4")
    }
}
