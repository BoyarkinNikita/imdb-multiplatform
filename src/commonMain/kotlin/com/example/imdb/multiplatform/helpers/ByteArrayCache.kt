package com.example.imdb.multiplatform.helpers

interface ByteArrayCache {
    fun cache(key: String, bytes: ByteArray, aliveTimeMs: ULong): Boolean
    fun restoreOrNull(key: String): ByteArray?
    fun removeIfExists(key: String): Boolean
    fun clear(): Boolean
}