package com.example.imdb.multiplatform.helper

import com.example.imdb.multiplatform.helpers.ByteArrayCache

// TODO: Implement WebByteArrayCache
class WebByteArrayCache : ByteArrayCache {
    override fun cache(key: String, bytes: ByteArray, aliveTimeMs: ULong): Boolean = false

    override fun restoreOrNull(key: String): ByteArray? = null

    override fun removeIfExists(key: String): Boolean = false
}