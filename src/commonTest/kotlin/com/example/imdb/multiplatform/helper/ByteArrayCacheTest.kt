package com.example.imdb.multiplatform.helper

import com.example.imdb.multiplatform.helpers.ByteArrayCache
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

abstract class ByteArrayCacheTest {

    protected abstract val byteArrayCache: ByteArrayCache

    @AfterTest
    fun afterTest() {
        byteArrayCache.clear()
    }

    @Test
    fun testCache() {
        val keyExample = "abcdef123456"

        val initialBytes = ByteArray(Byte.MAX_VALUE.toInt()) { it.toByte() }

        val cacheResult = byteArrayCache.cache(
            key = keyExample,
            bytes = initialBytes,
            aliveTimeMs = Long.MAX_VALUE.toULong()
        )

        assertTrue(cacheResult)

        val restoredBytes = byteArrayCache.restoreOrNull(key = keyExample)
        assertContentEquals(restoredBytes, initialBytes)
    }

    @Test
    fun testExpiration() {
        val keyExample = "abcdef123456"

        val initialBytes = ByteArray(Byte.MAX_VALUE.toInt()) { it.toByte() }

        val cacheResult = byteArrayCache.cache(
            key = keyExample,
            bytes = initialBytes,
            aliveTimeMs = 0u
        )

        assertTrue(cacheResult)

        val restoredBytes = byteArrayCache.restoreOrNull(key = keyExample)
        assertNull(restoredBytes)
    }

    @Test
    fun testRemove() {
        val keyExample = "abcdef123456"

        val initialBytes = ByteArray(Byte.MAX_VALUE.toInt()) { it.toByte() }

        val cacheResult = byteArrayCache.cache(
            key = keyExample,
            bytes = initialBytes,
            aliveTimeMs = Long.MAX_VALUE.toULong()
        )

        assertTrue(cacheResult)

        val removeResult = byteArrayCache.removeIfExists(key = keyExample)
        assertTrue(removeResult)

        val restoredBytes = byteArrayCache.restoreOrNull(key = keyExample)
        assertNull(restoredBytes)
    }

    @Test
    fun testClear() {
        val firstKeyExample = "abcdef123456"
        val secondKeyExample = "654321fedcba"

        val initialBytes = ByteArray(Byte.MAX_VALUE.toInt()) { it.toByte() }

        val firstCacheResult = byteArrayCache.cache(
            key = firstKeyExample,
            bytes = initialBytes,
            aliveTimeMs = Long.MAX_VALUE.toULong()
        )

        assertTrue(firstCacheResult)

        val secondCacheResult = byteArrayCache.cache(
            key = secondKeyExample,
            bytes = initialBytes,
            aliveTimeMs = Long.MAX_VALUE.toULong()
        )

        assertTrue(secondCacheResult)

        val firstRestoredBytes = byteArrayCache.restoreOrNull(key = firstKeyExample)
        assertContentEquals(firstRestoredBytes, initialBytes)

        val secondRestoredBytes = byteArrayCache.restoreOrNull(key = secondKeyExample)
        assertContentEquals(secondRestoredBytes, initialBytes)

        byteArrayCache.clear()

        val firstRestoredBytesAfterClear = byteArrayCache.restoreOrNull(key = firstKeyExample)
        assertNull(firstRestoredBytesAfterClear)

        val secondRestoredBytesAfterClear = byteArrayCache.restoreOrNull(key = secondKeyExample)
        assertNull(secondRestoredBytesAfterClear)
    }
}