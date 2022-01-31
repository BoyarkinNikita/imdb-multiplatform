package com.example.imdb.multiplatform.helper

import com.example.imdb.multiplatform.di.provide
import com.example.imdb.multiplatform.helpers.ByteArrayCache
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import java.io.File
import java.nio.ByteBuffer
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.locks.ReadWriteLock
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.withLock

class DesktopByteArrayCache : ByteArrayCache {
    private val locks = ConcurrentHashMap<String, ReadWriteLock>()

    private val cacheDir: File? = runCatching {
        System.getProperty("java.io.tmpdir")?.let { path ->
            File(path, "imdb_DesktopByteArrayCache_byteCache").apply {
                if (!exists()) mkdir()
            }
        }
    }.getOrNull()

    init {
        CoroutineScope(provide<Job>() + Default).launch {
            // Remove all expired files.
            cacheDirectoryOrNull()?.listFiles()?.forEach { file ->
                file?.name?.let { restoreOrNull(it) }
            }
        }
    }

    override fun cache(key: String, bytes: ByteArray, aliveTimeMs: ULong): Boolean = runCatching {
        val cacheFile = cacheFileOrNull(key = key, createIfNotExists = true) ?: return false
        val expiration = Clock.System.now().toEpochMilliseconds().toULong() + aliveTimeMs

        lock(key).writeLock().withLock {
            val expirationBytes = ByteBuffer.allocate(Long.SIZE_BYTES).apply {
                putLong(expiration.toLong())
            }.array()

            cacheFile.writeBytes(expirationBytes + bytes)
        }
    }.isSuccess

    override fun restoreOrNull(key: String): ByteArray? = runCatching {
        val cacheFile = cacheFileOrNull(key = key, createIfNotExists = false) ?: return null
        val currentTime = Clock.System.now().toEpochMilliseconds().toULong()

        var isExpired = false

        lock(key).readLock().withLock {
            val bytes = cacheFile.readBytes()
            if (bytes.size < Long.SIZE_BYTES) return null

            val expirationBytes = bytes.copyOfRange(0, Long.SIZE_BYTES)
            val expiration = ByteBuffer.allocate(Long.SIZE_BYTES).apply {
                put(expirationBytes)
                flip()
            }.long.toULong()

            if (expiration < currentTime) {
                isExpired = true
                null
            } else bytes.copyOfRange(Long.SIZE_BYTES, bytes.size)
        }.also { if (isExpired) removeIfExists(key) }
    }.getOrNull()

    override fun removeIfExists(key: String): Boolean = runCatching {
        val cacheFile = cacheFileOrNull(key = key, createIfNotExists = false) ?: return false
        lock(key).writeLock().withLock { cacheFile.delete() }
    }.getOrNull() ?: false

    private fun lock(key: String): ReadWriteLock =
        locks.getOrPut(key) { ReentrantReadWriteLock() }

    private fun cacheDirectoryOrNull(): File? {
        val cacheDir = cacheDir ?: return null
        if (cacheDir.run { !exists() || !canRead() || !canWrite() || !isDirectory }) return null
        return cacheDir
    }

    private fun cacheFileOrNull(key: String, createIfNotExists: Boolean): File? {
        val cacheDir = cacheDirectoryOrNull() ?: return null
        val cacheFile = File(cacheDir, key)
        if (createIfNotExists && !cacheFile.exists()) cacheFile.createNewFile()
        if (cacheFile.run { !exists() || !canRead() || !canWrite() || !isFile }) return null
        return cacheFile
    }
}