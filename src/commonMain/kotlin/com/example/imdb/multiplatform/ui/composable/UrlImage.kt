package com.example.imdb.multiplatform.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import com.example.imdb.multiplatform.di.provide
import com.example.imdb.multiplatform.repository.image.ImageRepository
import com.example.imdb.multiplatform.res.icons.DefaultPlaceholder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun UrlImage(
    url: String?,
    placeholder: ImageVector = Icons.Filled.DefaultPlaceholder,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    filterQuality: FilterQuality = DrawScope.DefaultFilterQuality
) {
    var bitmap by remember { mutableStateOf<ImageBitmap?>(null) }

    url?.let {
        LaunchedEffect(url) {
            bitmap = withContext(Dispatchers.Default) {
                runCatching { provide<ImageRepository>().loadImage(url) }.getOrNull()
            }
        }
    }

    bitmap?.let { imageBitmap ->
        Image(
            modifier = modifier,
            bitmap = imageBitmap,
            contentDescription = contentDescription,
            alignment = alignment,
            contentScale = contentScale,
            alpha = alpha,
            colorFilter = colorFilter,
            filterQuality = filterQuality
        )
    } ?: Image(
        modifier = modifier,
        imageVector = placeholder,
        contentDescription = contentDescription,
        alignment = alignment,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = colorFilter
    )
}