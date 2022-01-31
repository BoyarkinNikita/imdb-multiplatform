package com.example.imdb.multiplatform.di

import com.example.imdb.multiplatform.helper.WebByteArrayCache
import com.example.imdb.multiplatform.helpers.ByteArrayCache
import org.koin.dsl.module

val webModule = module {
    single<ByteArrayCache> { WebByteArrayCache() }
}