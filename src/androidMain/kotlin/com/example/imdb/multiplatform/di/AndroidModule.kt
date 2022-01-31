package com.example.imdb.multiplatform.di

import android.content.Context
import com.example.imdb.multiplatform.helper.AndroidByteArrayCache
import com.example.imdb.multiplatform.helpers.ByteArrayCache
import org.koin.dsl.module

val androidModule = module {
    single<ByteArrayCache> { AndroidByteArrayCache(provide<Context>()) }
}