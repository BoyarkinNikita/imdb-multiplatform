package com.example.imdb.multiplatform.di

import com.example.imdb.multiplatform.helpers.ByteArrayCache
import com.example.imdb.multiplatform.helpers.CacheManager
import com.example.imdb.multiplatform.helpers.ViewModelStore
import com.goncalossilva.murmurhash.MurmurHash3
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

val commonModule = module {
    single<Job> { SupervisorJob() }

    single<ViewModelStore> { ViewModelStore() }

    single<MurmurHash3> { MurmurHash3() }

    single<CacheManager> {
        CacheManager(
            hash = provide<MurmurHash3>(),
            byteArrayCache = provide<ByteArrayCache>()
        )
    }
}