package com.example.imdb.multiplatform.helper

import com.example.imdb.multiplatform.helpers.ByteArrayCache

class AndroidByteArrayCacheTest : ByteArrayCacheTest() {
    override val byteArrayCache: ByteArrayCache by lazy {
        AndroidByteArrayCache(
            baseCachePath = "/data/data/com.example.imdb.multiplatform.android/cache"
        )
    }
}