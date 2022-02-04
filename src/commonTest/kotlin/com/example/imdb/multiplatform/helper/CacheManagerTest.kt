package com.example.imdb.multiplatform.helper

import com.example.imdb.multiplatform.helpers.CacheManager
import com.goncalossilva.murmurhash.MurmurHash3
import kotlinx.serialization.Serializable
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CacheManagerTest {
    private val cacheManager: CacheManager = CacheManager(
        hash = MurmurHash3(),
        byteArrayCache = MemoryByteArrayCache()
    )

    @AfterTest
    fun afterTest() {
        cacheManager.clear()
    }

    @Test
    fun testObjectCache() {
        val arguments = arrayOf("testString", 1, 1.0, Custom(1))

        val initialObject = Object(
            testString = arguments[0] as String,
            testInt = arguments[1] as Int,
            testDouble = arguments[2] as Double,
            testCustom = arguments[3] as Custom
        )

        val cacheResult = cacheManager.cacheObject(
            any = initialObject,
            serializer = Object.serializer(),
            keyBuilder = arguments
        )

        assertTrue(cacheResult)

        val restoredObject = cacheManager.restoreObjectOrNull(
            keyBuilder = arguments,
            deserializer = Object.serializer()
        )

        assertEquals(restoredObject, initialObject)
    }

    @Test
    fun testCollectionCache() {
        val arguments = arrayOf("testString", 1, 1.0, Custom(1))

        val initialCollection = arrayListOf<Object>()

        repeat(10) { index ->
            initialCollection.add(
                Object(
                    testString = (arguments[0] as String) + index,
                    testInt = (arguments[1] as Int) + index,
                    testDouble = (arguments[2] as Double) + index,
                    testCustom = (arguments[3] as Custom) + index
                )
            )
        }

        val cacheResult = cacheManager.cacheCollection(
            collection = initialCollection,
            serializer = Object.serializer(),
            keyBuilder = arguments
        )

        assertTrue(cacheResult)

        val restoredCollection = cacheManager.restoreCollectionOrNull(
            keyBuilder = arguments,
            deserializer = Object.serializer()
        )

        assertEquals(initialCollection, restoredCollection)
    }

    @Test
    fun testBytesCache() {
        val arguments = arrayOf("testString", 1, 1.0, Custom(1))
        val initialBytes = ByteArray(Byte.MAX_VALUE.toInt()) { it.toByte() }

        val cacheResult = cacheManager.cacheBytes(
            bytes = initialBytes,
            keyBuilder = arguments
        )

        assertTrue(cacheResult)

        val restoredBytes = cacheManager.restoreBytesOrNull(keyBuilder = arguments)

        assertEquals(restoredBytes, initialBytes)
    }

    /** Test helper. */

    @Serializable
    private data class Object(
        val testString: String,
        val testInt: Int,
        val testDouble: Double,
        val testCustom: Custom
    )

    @Serializable
    private data class Custom(val testInt: Int) {
        operator fun plus(add: Int): Custom = Custom(testInt + add)
    }
}