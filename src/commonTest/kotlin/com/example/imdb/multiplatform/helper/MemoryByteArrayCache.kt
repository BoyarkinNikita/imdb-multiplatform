package com.example.imdb.multiplatform.helper

import com.example.imdb.multiplatform.helpers.ByteArrayCache
import kotlinx.datetime.Clock

class MemoryByteArrayCache : ByteArrayCache {
    private val byteArrayMemoryCache =
        hashMapOf<String, Pair<ByteArray, ULong>>()

    override fun cache(key: String, bytes: ByteArray, aliveTimeMs: ULong): Boolean {
        validateKey(key)
        val expiration = Clock.System.now().toEpochMilliseconds().toULong() + aliveTimeMs
        byteArrayMemoryCache[key] = bytes to expiration
        return true
    }

    override fun restoreOrNull(key: String): ByteArray? {
        validateKey(key)

        val currentTime = Clock.System.now().toEpochMilliseconds().toULong()
        val (bytes, expiration) = byteArrayMemoryCache[key] ?: return null

        return if (expiration < currentTime) {
            removeIfExists(key)
            null
        } else bytes
    }

    override fun removeIfExists(key: String): Boolean {
        validateKey(key)
        return byteArrayMemoryCache.remove(key) != null
    }

    override fun clear(): Boolean {
        byteArrayMemoryCache.clear()
        return true
    }

    private fun validateKey(key: String) {
        if (!key.matches("[a-z0-9]{1,64}".toRegex()))
            throw IllegalArgumentException("Illegal key $key")
    }
}