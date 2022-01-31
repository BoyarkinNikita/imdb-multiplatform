package com.example.imdb.multiplatform.di

import com.example.imdb.multiplatform.api.image.ImageApi
import com.example.imdb.multiplatform.api.imdb.ImdbApi
import io.ktor.client.HttpClient
import io.ktor.client.plugins.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.serialization.kotlinx.serialization
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single<HttpClient> {
        HttpClient {
            engine { threadsCount = 20 }

            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })

                serialization(
                    contentType = ContentType.Application.Json,
                    format = Json
                )
            }
        }
    }

    single<ImdbApi> { ImdbApi() }

    single<ImageApi> { ImageApi() }
}