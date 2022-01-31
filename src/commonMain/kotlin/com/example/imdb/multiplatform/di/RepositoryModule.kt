package com.example.imdb.multiplatform.di

import com.example.imdb.multiplatform.api.image.ImageApi
import com.example.imdb.multiplatform.api.imdb.ImdbApi
import com.example.imdb.multiplatform.helpers.CacheManager
import com.example.imdb.multiplatform.repository.image.ImageRepository
import com.example.imdb.multiplatform.repository.imdb.ImdbRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ImageRepository> {
        ImageRepository(
            api = provide<ImageApi>(),
            cache = provide<CacheManager>()
        )
    }

    single<ImdbRepository> {
        ImdbRepository(
            api = provide<ImdbApi>(),
            cache = provide<CacheManager>()
        )
    }
}