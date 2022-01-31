package com.example.imdb.multiplatform.res.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = if (isSystemInDarkTheme()) darkColors else lightColors,
        content = content
    )
}

private val lightColors = lightColors(
    primary = Color(0xFF745b00),
    primaryVariant = Color(0xFFffe07e),
    onPrimary = Color(0xFFffffff),
    secondary = Color(0xFF695e3f),
    secondaryVariant = Color(0xFFf1e1bb),
    onSecondary = Color(0xFFffffff),
    background = Color(0xFFfffbf7),
    onBackground = Color(0xFF1e1b16),
    surface = Color(0xFFfffbf7),
    onSurface = Color(0xFF1e1b16),
    error = Color(0xFFba1b1b),
    onError = Color(0xFFffffff)
)

private val darkColors = darkColors(
    primary = Color(0xFFeec117),
    primaryVariant = Color(0xFF574400),
    onPrimary = Color(0xFF3d2f00),
    secondary = Color(0xFFd4c5a0),
    secondaryVariant = Color(0xFF50462a),
    onSecondary = Color(0xFF383016),
    background = Color(0xFF1e1b16),
    onBackground = Color(0xFFe9e2d9),
    surface = Color(0xFF1e1b16),
    onSurface = Color(0xFFe9e2d9),
    error = Color(0xFFba1b1b),
    onError = Color(0xFF680003)
)