package com.example.imdb.multiplatform

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.example.imdb.multiplatform.android.BuildConfig

actual val isDebug: Boolean = BuildConfig.DEBUG

actual fun ByteArray.toImageBitmap(): ImageBitmap? =
    BitmapFactory.decodeByteArray(this, 0, size)?.asImageBitmap()