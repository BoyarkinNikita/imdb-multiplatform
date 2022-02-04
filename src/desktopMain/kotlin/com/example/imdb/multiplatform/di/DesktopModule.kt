package com.example.imdb.multiplatform.di

import com.example.imdb.multiplatform.helper.DesktopByteArrayCache
import com.example.imdb.multiplatform.helpers.ByteArrayCache
import kotlinx.coroutines.Job
import org.koin.dsl.module

val desktopModule = module {
    single<ByteArrayCache> {
        DesktopByteArrayCache(
            parentJob = provide<Job>(),
            baseCachePath = System.getProperty("java.io.tmpdir")
        )
    }
}