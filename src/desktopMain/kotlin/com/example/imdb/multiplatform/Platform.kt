package com.example.imdb.multiplatform

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image

actual val isDebug: Boolean = false

actual fun ByteArray.toImageBitmap(): ImageBitmap? =
    Image.makeFromEncoded(this).toComposeImageBitmap()
