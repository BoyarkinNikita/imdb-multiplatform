package com.example.imdb.multiplatform.di

import com.example.imdb.multiplatform.helper.AndroidByteArrayCache
import com.example.imdb.multiplatform.helpers.ByteArrayCache
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule = module {
    single<ByteArrayCache> {
        AndroidByteArrayCache(baseCachePath = androidContext().cacheDir?.canonicalPath)
    }
}