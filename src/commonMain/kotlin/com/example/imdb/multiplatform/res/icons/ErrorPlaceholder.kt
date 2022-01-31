package com.example.imdb.multiplatform.res.icons

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Filled.ErrorPlaceholder: ImageVector
    get() {
        if (_errorPlaceholder != null) {
            return _errorPlaceholder!!
        }
        _errorPlaceholder = Builder(
            name = "ErrorPlaceholder", defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(21.0f, 5.0f)
                verticalLineToRelative(6.59f)
                lineToRelative(-3.0f, -3.01f)
                lineToRelative(-4.0f, 4.01f)
                lineToRelative(-4.0f, -4.0f)
                lineToRelative(-4.0f, 4.0f)
                lineToRelative(-3.0f, -3.01f)
                lineTo(3.0f, 5.0f)
                curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f)
                horizontalLineToRelative(14.0f)
                curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f)
                close()
                moveTo(18.0f, 11.42f)
                lineToRelative(3.0f, 3.01f)
                lineTo(21.0f, 19.0f)
                curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f)
                lineTo(5.0f, 21.0f)
                curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f)
                verticalLineToRelative(-6.58f)
                lineToRelative(3.0f, 2.99f)
                lineToRelative(4.0f, -4.0f)
                lineToRelative(4.0f, 4.0f)
                lineToRelative(4.0f, -3.99f)
                close()
            }
        }
            .build()
        return _errorPlaceholder!!
    }

private var _errorPlaceholder: ImageVector? = null
