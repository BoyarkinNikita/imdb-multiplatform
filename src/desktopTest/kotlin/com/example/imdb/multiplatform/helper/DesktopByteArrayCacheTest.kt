package com.example.imdb.multiplatform.helper

import com.example.imdb.multiplatform.helpers.ByteArrayCache
import kotlinx.coroutines.SupervisorJob

class DesktopByteArrayCacheTest : ByteArrayCacheTest() {
    override val byteArrayCache: ByteArrayCache by lazy {
        DesktopByteArrayCache(
            parentJob = SupervisorJob(),
            baseCachePath = System.getProperty("java.io.tmpdir")
        )
    }
}