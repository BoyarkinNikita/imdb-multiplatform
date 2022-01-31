package com.example.imdb.multiplatform.api.image

import com.example.imdb.multiplatform.di.provide
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.readBytes
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.utils.io.errors.IOException

class ImageApi {
    @Throws(IOException::class, RuntimeException::class)
    suspend fun loadImageBytes(imageUrl: String): ByteArray =
        provide<HttpClient>().get(imageUrl) {
            method = HttpMethod.Get
            contentType(ContentType.Image.Any)
        }.readBytes()
}