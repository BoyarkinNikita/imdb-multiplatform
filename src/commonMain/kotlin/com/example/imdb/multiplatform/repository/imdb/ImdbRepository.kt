package com.example.imdb.multiplatform.repository.imdb

import com.example.imdb.multiplatform.api.imdb.ImdbApi
import com.example.imdb.multiplatform.data.details.ImdbDetailsItem
import com.example.imdb.multiplatform.data.search.ImdbSearchResultItem
import com.example.imdb.multiplatform.helpers.CacheManager
import io.ktor.utils.io.errors.IOException
import kotlin.time.Duration.Companion.minutes

class ImdbRepository(
    val api: ImdbApi,
    val cache: CacheManager
) {
    @Throws(IOException::class, RuntimeException::class)
    suspend fun details(imdbId: String): ImdbDetailsItem {
        val keyBuilder = arrayOf(imdbId)

        return cache.restoreObjectOrNull(
            deserializer = ImdbDetailsItem.serializer(),
            keyBuilder = keyBuilder
        ) ?: api.details(imdbId).also { item ->
            cache.cacheObject(
                any = item,
                serializer = ImdbDetailsItem.serializer(),
                keyBuilder = keyBuilder,
                aliveTimeMs = IMDB_CACHE_TIME_MS
            )
        }
    }

    @Throws(IOException::class, RuntimeException::class)
    suspend fun search(query: String, page: UInt): ImdbSearchResultItem {
        val keyBuilder = arrayOf(query, page)

        return cache.restoreObjectOrNull(
            deserializer = ImdbSearchResultItem.serializer(),
            keyBuilder = keyBuilder
        ) ?: api.search(query, page).also { item ->
            cache.cacheObject(
                any = item,
                serializer = ImdbSearchResultItem.serializer(),
                keyBuilder = keyBuilder,
                aliveTimeMs = IMDB_CACHE_TIME_MS
            )
        }
    }

    companion object {
        private val IMDB_CACHE_TIME_MS = 10.minutes.inWholeMilliseconds.toULong()
    }
}