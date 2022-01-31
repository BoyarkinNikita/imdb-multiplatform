package com.example.imdb.multiplatform.helper

import android.content.Context
import com.example.imdb.multiplatform.android.BuildConfig.VERSION_CODE
import com.example.imdb.multiplatform.helpers.ByteArrayCache
import com.jakewharton.disklrucache.DiskLruCache
import kotlinx.datetime.Clock
import java.io.File
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.locks.ReadWriteLock
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.withLock

class AndroidByteArrayCache(context: Context) : ByteArrayCache {

    private val locks = ConcurrentHashMap<String, ReadWriteLock>()
    private val lruCache: DiskLruCache

    init {
        val cacheDir = File(context.cacheDir, "byteCache")

        lruCache = DiskLruCache.open(
            cacheDir, VERSION_CODE,
            2, 50L * 1_024 * 1_024
        )
    }

    override fun cache(key: String, bytes: ByteArray, aliveTimeMs: ULong): Boolean = runCatching {
        val expiration = Clock.System.now().toEpochMilliseconds().toULong() + aliveTimeMs

        lock(key).writeLock().withLock {
            lruCache.edit(key).apply {
                newOutputStream(0).use {
                    it.write(bytes)
                    it.flush()
                }

                set(1, expiration.toString())
                commit()
            }
        }
    }.isSuccess

    override fun restoreOrNull(key: String): ByteArray? = runCatching {
        val currentTime = Clock.System.now().toEpochMilliseconds().toULong()

        var isExpired = false

        return lock(key).readLock().withLock {
            val snapshot = lruCache.get(key) ?: return null
            val expiration = snapshot.getString(1)?.toULongOrNull() ?: return null

            if (expiration < currentTime) {
                isExpired = true
                null
            } else snapshot.getInputStream(0)?.use { it.readBytes() }
        }.also { if (isExpired) removeIfExists(key) }
    }.getOrNull()

    override fun removeIfExists(key: String): Boolean = runCatching {
        lock(key).writeLock().withLock { lruCache.remove(key) }
    }.getOrNull() ?: false

    private fun lock(key: String): ReadWriteLock =
        locks.getOrPut(key) { ReentrantReadWriteLock() }
}