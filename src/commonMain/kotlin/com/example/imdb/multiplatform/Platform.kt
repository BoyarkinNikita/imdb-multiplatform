package com.example.imdb.multiplatform

import androidx.compose.ui.graphics.ImageBitmap

expect val isDebug: Boolean

expect fun ByteArray.toImageBitmap(): ImageBitmap?

//expect fun createByteArrayCache(): ByteArrayCache