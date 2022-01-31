package com.example.imdb.multiplatform.repository.image

import androidx.compose.ui.graphics.ImageBitmap
import com.example.imdb.multiplatform.api.image.ImageApi
import com.example.imdb.multiplatform.helpers.CacheManager
import com.example.imdb.multiplatform.toImageBitmap
import io.ktor.utils.io.errors.IOException
import kotlin.time.Duration.Companion.hours

class ImageRepository(
    val api: ImageApi,
    val cache: CacheManager
) {
    @Throws(IOException::class, RuntimeException::class)
    suspend fun loadImage(imageUrl: String): ImageBitmap? {
        val keyBuilder = arrayOf(imageUrl)
        val bytes =
            cache.restoreBytesOrNull(keyBuilder = keyBuilder) ?: api.loadImageBytes(imageUrl).also { bytes ->
                cache.cacheBytes(
                    bytes = bytes,
                    keyBuilder = keyBuilder,
                    aliveTimeMs = IMAGE_CACHE_TIME_MS
                )
            }

        return bytes.toImageBitmap()
    }

    companion object {
        private val IMAGE_CACHE_TIME_MS = 1.hours.inWholeMilliseconds.toULong()
    }
}