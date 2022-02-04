package com.example.imdb.multiplatform.helpers

import com.goncalossilva.murmurhash.MurmurHash3
import io.ktor.utils.io.core.toByteArray
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.cbor.Cbor
import kotlin.math.ceil
import kotlin.math.log
import kotlin.math.min
import kotlin.math.pow

@OptIn(ExperimentalSerializationApi::class)
class CacheManager(
    private val hash: MurmurHash3,
    private val byteArrayCache: ByteArrayCache
) {

    fun cacheBytes(
        bytes: ByteArray,
        keyBuilder: Array<*>,
        aliveTimeMs: ULong = Long.MAX_VALUE.toULong()
    ): Boolean = internalCache(keyBuilder, aliveTimeMs) { bytes }

    fun <T : Any> cacheCollection(
        collection: Collection<T>,
        serializer: KSerializer<T>,
        keyBuilder: Array<*>,
        aliveTimeMs: ULong = Long.MAX_VALUE.toULong()
    ): Boolean = internalCache(keyBuilder, aliveTimeMs) {
        val arrayList = if (collection is ArrayList<T>) collection else ArrayList(collection)
        Cbor.encodeToByteArray(ListSerializer(serializer), arrayList)
    }

    fun <T : Any> cacheObject(
        any: T,
        serializer: KSerializer<T>,
        keyBuilder: Array<*>,
        aliveTimeMs: ULong = Long.MAX_VALUE.toULong()
    ): Boolean = internalCache(keyBuilder, aliveTimeMs) {
        Cbor.encodeToByteArray(serializer, any)
    }

    fun restoreBytesOrNull(keyBuilder: Array<*>): ByteArray? =
        internalRestore(keyBuilder) { bytes -> bytes }

    fun <T : Any> restoreCollectionOrNull(
        deserializer: KSerializer<T>,
        keyBuilder: Array<*>
    ): Collection<T>? = internalRestore(keyBuilder) { bytes ->
        Cbor.decodeFromByteArray(ListSerializer(deserializer), bytes)
    }

    fun <T : Any> restoreObjectOrNull(
        deserializer: KSerializer<T>,
        keyBuilder: Array<*>
    ): T? = internalRestore(keyBuilder) { bytes ->
        Cbor.decodeFromByteArray(deserializer, bytes)
    }

    fun removeIfExists(keyBuilder: Array<*>): Boolean = runCatching {
        byteArrayCache.removeIfExists(cacheKey(keyBuilder))
    }.getOrNull() ?: false

    fun clear(): Boolean = byteArrayCache.clear()

    private inline fun internalCache(
        keyBuilder: Array<*>,
        aliveTimeMs: ULong = Long.MAX_VALUE.toULong(),
        serialize: () -> ByteArray
    ): Boolean = runCatching {
        val bytes = serialize()
        byteArrayCache.cache(cacheKey(keyBuilder), bytes, aliveTimeMs)
    }.getOrNull() ?: false

    private inline fun <T> internalRestore(
        keyBuilder: Array<*>,
        deserialize: (ByteArray) -> T
    ): T? = runCatching {
        byteArrayCache.restoreOrNull(cacheKey(keyBuilder))?.let { bytes ->
            deserialize(bytes)
        }
    }.getOrNull()

    private fun cacheKey(keyBuilder: Array<*>): String {
        val bytes = keyBuilder.joinToString(separator = "", prefix = "", transform = {
            it.toString()
        }).toByteArray()

        val chunkSize = ceil(bytes.size.toDouble() / COUNT_OF_SHA128_PARTITIONS).toInt()

        val hashArray = Array(COUNT_OF_SHA128_PARTITIONS * LONGS_IN_SHA128) { 0uL }

        if (chunkSize > 0) {
            repeat(COUNT_OF_SHA128_PARTITIONS) { partitionIndex ->
                val from = partitionIndex * chunkSize
                if (from < bytes.size) {
                    val toHash = bytes.copyOfRange(from, min(bytes.size, from + chunkSize))
                    if (toHash.isNotEmpty()) {
                        val hashResult = hash.hash128x64(toHash)
                        repeat(LONGS_IN_SHA128) { longIndex ->
                            hashArray[partitionIndex * LONGS_IN_SHA128 + longIndex] = hashResult[longIndex]
                        }
                    }
                }
            }
        }

        return buildString { hashArray.forEach { append(it.toString(KEY_RADIX)) } }
    }

    companion object {
        private const val MAX_KEY_SIZE_BYTES = 64
        private const val LONGS_IN_SHA128 = 2
        private const val KEY_RADIX = 36

        private val DIGITS_FOR_LONG = ceil(log(2.0.pow(Byte.SIZE_BITS), KEY_RADIX.toDouble()) * Long.SIZE_BYTES).toInt()
        private val COUNT_OF_SHA128_PARTITIONS = MAX_KEY_SIZE_BYTES / DIGITS_FOR_LONG / LONGS_IN_SHA128
    }
}