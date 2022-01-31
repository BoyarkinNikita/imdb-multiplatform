package com.example.imdb.multiplatform.res.icons

import androidx.compose.material.icons.Icons
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.radialGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Filled.ImdbLogo: ImageVector
    get() {
        if (_imdbLogo != null) {
            return _imdbLogo!!
        }
        _imdbLogo = Builder(
            name = "imdbLogo", defaultWidth = 150.0.dp, defaultHeight = 73.0.dp,
            viewportWidth = 3000.0f, viewportHeight = 1460.7986f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = EvenOdd
            ) {
                moveToRelative(120.88f, 432.66f)
                curveToRelative(-55.71f, 0.0f, -100.88f, 45.08f, -100.88f, 100.7f)
                lineToRelative(0.0f, 805.6f)
                curveToRelative(0.0f, 55.62f, 45.17f, 100.7f, 100.88f, 100.7f)
                lineToRelative(1898.24f, 0.0f)
                curveToRelative(55.71f, 0.0f, 100.88f, -45.08f, 100.88f, -100.7f)
                lineToRelative(0.0f, -805.6f)
                curveToRelative(0.0f, -55.62f, -45.17f, -100.7f, -100.88f, -100.7f)
                lineToRelative(-1898.24f, 0.0f)
                close()
            }
            path(
                fill = radialGradient(
                    0.0f to Color(0xFFFCF06E), 0.99f to Color(0xFFDBA506), center
                    = Offset(1475.0084f, 783.1783f), radius = 1194.3666f
                ), stroke = null,
                strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = EvenOdd
            ) {
                moveToRelative(2980.0f, 1316.97f)
                lineToRelative(0.0f, -1174.87f)
                curveToRelative(-8.01f, -62.55f, -56.78f, -112.38f, -118.88f, -122.11f)
                lineToRelative(-2721.09f, 0.0f)
                curveTo(72.03f, 30.66f, 20.0f, 89.39f, 20.0f, 160.28f)
                lineTo(20.0f, 1298.8f)
                curveToRelative(0.0f, 78.43f, 63.7f, 142.0f, 142.26f, 142.0f)
                lineToRelative(2676.64f, 0.0f)
                curveToRelative(72.39f, 0.0f, 132.16f, -53.98f, 141.09f, -123.83f)
                close()
            }
            path(
                fill = radialGradient(
                    0.0f to Color(0xFFFFFFFF), 1.0f to Color(0x00FFFFFF), center
                    = Offset(1488.7231f, 84.40446f), radius = 1119.6821f
                ), stroke = null, fillAlpha =
                0.4f, strokeAlpha = 0.4f, strokeLineWidth = 0.0f, strokeLineCap = Butt,
                strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType = EvenOdd
            ) {
                moveToRelative(146.86f, 20.51f)
                lineToRelative(2706.29f, 0.0f)
                lineToRelative(0.0f, 497.06f)
                lineToRelative(-2706.29f, 0.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = EvenOdd
            ) {
                moveToRelative(428.76f, 300.5f)
                lineToRelative(223.72f, 0.0f)
                lineToRelative(0.0f, 861.22f)
                lineToRelative(-223.72f, 0.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFfbfbed)), stroke = null, fillAlpha = 0.3f, strokeAlpha
                = 0.3f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveToRelative(652.48f, 300.5f)
                lineToRelative(0.0f, 861.22f)
                lineToRelative(-223.72f, 0.0f)
                lineToRelative(0.0f, -861.22f)
                lineToRelative(223.72f, 0.0f)
                moveToRelative(19.73f, -19.73f)
                lineToRelative(-19.73f, 0.0f)
                lineToRelative(-223.72f, 0.0f)
                lineToRelative(-19.73f, 0.0f)
                lineToRelative(0.0f, 19.73f)
                lineToRelative(0.0f, 861.22f)
                lineToRelative(0.0f, 19.73f)
                lineToRelative(19.73f, 0.0f)
                lineToRelative(223.72f, 0.0f)
                lineToRelative(19.73f, 0.0f)
                lineToRelative(0.0f, -19.73f)
                lineToRelative(0.0f, -861.22f)
                lineToRelative(0.0f, -19.73f)
                lineToRelative(0.0f, 0.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = EvenOdd
            ) {
                moveToRelative(1129.61f, 300.5f)
                lineToRelative(-51.81f, 402.33f)
                lineToRelative(-32.05f, -218.79f)
                curveToRelative(-9.35f, -70.22f, -18.28f, -131.41f, -26.81f, -183.55f)
                lineToRelative(-290.05f, 0.0f)
                lineToRelative(0.0f, 861.22f)
                lineToRelative(195.95f, 0.0f)
                lineToRelative(0.7f, -568.64f)
                lineToRelative(82.47f, 568.64f)
                lineToRelative(139.57f, 0.0f)
                lineToRelative(78.24f, -581.3f)
                lineToRelative(0.7f, 581.3f)
                lineToRelative(195.33f, 0.0f)
                lineToRelative(0.0f, -861.22f)
                lineToRelative(-292.25f, 0.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFfbfbed)), stroke = null, fillAlpha = 0.3f, strokeAlpha
                = 0.3f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveToRelative(1421.86f, 300.5f)
                lineToRelative(0.0f, 861.22f)
                lineToRelative(-195.33f, 0.0f)
                lineToRelative(-0.7f, -581.3f)
                lineToRelative(-78.24f, 581.3f)
                lineToRelative(-139.57f, 0.0f)
                lineToRelative(-82.47f, -568.64f)
                lineToRelative(-0.7f, 568.64f)
                lineToRelative(-195.95f, 0.0f)
                lineToRelative(0.0f, -861.22f)
                lineToRelative(290.05f, 0.0f)
                curveToRelative(8.53f, 52.14f, 17.46f, 113.33f, 26.81f, 183.55f)
                lineToRelative(32.05f, 218.79f)
                lineToRelative(51.81f, -402.33f)
                lineToRelative(292.25f, 0.0f)
                moveToRelative(19.73f, -19.73f)
                lineToRelative(-19.73f, 0.0f)
                lineToRelative(-292.25f, 0.0f)
                lineToRelative(-17.36f, 0.0f)
                lineToRelative(-2.22f, 17.21f)
                lineToRelative(-33.5f, 260.13f)
                lineToRelative(-11.25f, -76.79f)
                curveTo(1055.96f, 411.25f, 1046.91f, 349.35f, 1038.4f, 297.31f)
                lineToRelative(-2.71f, -16.55f)
                lineToRelative(-16.77f, 0.0f)
                lineToRelative(-290.05f, 0.0f)
                lineToRelative(-19.73f, 0.0f)
                lineToRelative(0.0f, 19.73f)
                lineToRelative(0.0f, 861.22f)
                lineToRelative(0.0f, 19.73f)
                lineToRelative(19.73f, 0.0f)
                lineToRelative(195.95f, 0.0f)
                lineToRelative(19.71f, 0.0f)
                lineToRelative(0.03f, -19.71f)
                lineToRelative(0.37f, -297.44f)
                lineToRelative(43.55f, 300.25f)
                lineToRelative(2.45f, 16.9f)
                lineToRelative(17.08f, 0.0f)
                lineToRelative(139.57f, 0.0f)
                lineToRelative(17.26f, 0.0f)
                lineToRelative(2.3f, -17.1f)
                lineToRelative(39.31f, -292.03f)
                lineToRelative(0.35f, 289.42f)
                lineToRelative(0.02f, 19.71f)
                lineToRelative(19.71f, 0.0f)
                lineToRelative(195.33f, 0.0f)
                lineToRelative(19.73f, 0.0f)
                lineToRelative(0.0f, -19.73f)
                lineToRelative(0.0f, -861.22f)
                lineToRelative(0.0f, -19.74f)
                lineToRelative(0.0f, 0.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = EvenOdd
            ) {
                moveToRelative(1771.71f, 455.49f)
                curveToRelative(8.84f, 5.12f, 14.51f, 13.19f, 16.97f, 24.15f)
                curveToRelative(2.47f, 10.98f, 3.72f, 35.93f, 3.72f, 74.86f)
                lineToRelative(0.0f, 333.87f)
                curveToRelative(0.0f, 57.34f, -3.72f, 92.45f, -11.15f, 105.36f)
                curveToRelative(-7.43f, 12.94f, -27.23f, 19.38f, -59.38f, 19.38f)
                lineToRelative(0.0f, -565.32f)
                curveToRelative(24.37f, 0.0f, 41.0f, 2.57f, 49.84f, 7.7f)
                close()
                moveTo(1769.4f, 1161.71f)
                curveToRelative(53.59f, 0.0f, 93.7f, -2.92f, 120.35f, -8.77f)
                curveToRelative(26.63f, -5.84f, 48.98f, -16.11f, 67.11f, -30.8f)
                curveToRelative(18.1f, -14.69f, 30.78f, -35.04f, 38.07f, -61.09f)
                curveToRelative(7.27f, -26.0f, 11.6f, -77.64f, 11.6f, -154.82f)
                lineToRelative(0.0f, -302.46f)
                curveToRelative(0.0f, -81.46f, -3.19f, -136.08f, -8.15f, -163.87f)
                curveToRelative(-4.98f, -27.78f, -17.39f, -53.01f, -37.28f, -75.68f)
                curveToRelative(-19.87f, -22.67f, -48.91f, -38.95f, -87.08f, -48.85f)
                curveToRelative(-38.15f, -9.91f, -100.46f, -14.88f, -208.74f, -14.88f)
                lineToRelative(-166.92f, 0.0f)
                lineToRelative(0.0f, 861.22f)
                lineToRelative(271.04f, 0.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFfbfbed)), stroke = null, fillAlpha = 0.3f, strokeAlpha
                = 0.3f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveToRelative(1665.28f, 300.5f)
                curveToRelative(108.28f, 0.0f, 170.58f, 4.98f, 208.74f, 14.88f)
                curveToRelative(38.17f, 9.91f, 67.21f, 26.19f, 87.08f, 48.85f)
                curveToRelative(19.89f, 22.67f, 32.31f, 47.9f, 37.28f, 75.68f)
                curveToRelative(4.96f, 27.8f, 8.15f, 82.41f, 8.15f, 163.87f)
                lineToRelative(0.0f, 302.46f)
                curveToRelative(0.0f, 77.19f, -4.33f, 128.82f, -11.6f, 154.82f)
                curveToRelative(-7.29f, 26.05f, -19.97f, 46.4f, -38.07f, 61.09f)
                curveToRelative(-18.13f, 14.69f, -40.48f, 24.96f, -67.11f, 30.8f)
                curveToRelative(-26.64f, 5.85f, -66.75f, 8.77f, -120.35f, 8.77f)
                lineToRelative(-271.04f, 0.0f)
                lineToRelative(0.0f, -861.22f)
                lineToRelative(166.92f, 0.0f)
                moveToRelative(56.59f, 712.61f)
                curveToRelative(32.15f, 0.0f, 51.95f, -6.44f, 59.38f, -19.38f)
                curveToRelative(7.43f, -12.91f, 11.15f, -48.02f, 11.15f, -105.36f)
                lineToRelative(0.0f, -333.87f)
                curveToRelative(0.0f, -38.93f, -1.25f, -63.88f, -3.72f, -74.86f)
                curveToRelative(-2.47f, -10.95f, -8.13f, -19.03f, -16.97f, -24.15f)
                curveToRelative(-8.84f, -5.13f, -25.47f, -7.7f, -49.84f, -7.7f)
                lineToRelative(0.0f, 565.32f)
                moveToRelative(-56.59f, -732.35f)
                lineToRelative(-166.92f, 0.0f)
                lineToRelative(-19.73f, 0.0f)
                lineToRelative(0.0f, 19.73f)
                lineToRelative(0.0f, 861.22f)
                lineToRelative(0.0f, 19.73f)
                lineToRelative(19.73f, 0.0f)
                lineToRelative(271.04f, 0.0f)
                curveToRelative(55.56f, 0.0f, 96.31f, -3.02f, 124.58f, -9.23f)
                curveToRelative(29.48f, -6.46f, 54.82f, -18.15f, 75.3f, -34.74f)
                curveToRelative(21.26f, -17.26f, 36.28f, -41.18f, 44.65f, -71.11f)
                curveToRelative(8.18f, -29.25f, 12.33f, -83.13f, 12.33f, -160.14f)
                lineToRelative(0.0f, -302.46f)
                curveToRelative(0.0f, -78.49f, -2.92f, -136.35f, -8.45f, -167.34f)
                curveToRelative(-5.6f, -31.27f, -19.69f, -59.94f, -41.88f, -85.23f)
                curveToRelative(-22.42f, -25.57f, -55.05f, -44.06f, -96.95f, -54.94f)
                curveToRelative(-41.32f, -10.73f, -107.23f, -15.52f, -213.7f, -15.52f)
                lineToRelative(0.0f, 0.0f)
                close()
                moveTo(1741.61f, 992.29f)
                lineTo(1741.61f, 468.27f)
                curveToRelative(13.67f, 1.18f, 18.73f, 3.43f, 20.2f, 4.28f)
                curveToRelative(2.72f, 1.57f, 6.02f, 4.28f, 7.63f, 11.42f)
                curveToRelative(1.21f, 5.38f, 3.24f, 21.7f, 3.24f, 70.52f)
                lineToRelative(0.0f, 333.87f)
                curveToRelative(0.0f, 74.89f, -6.52f, 92.05f, -8.52f, 95.52f)
                curveToRelative(-0.44f, 0.77f, -4.11f, 6.08f, -22.55f, 8.4f)
                lineToRelative(0.0f, 0.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = EvenOdd
            ) {
                moveToRelative(2373.69f, 952.74f)
                curveToRelative(0.0f, 41.68f, -2.07f, 68.01f, -6.2f, 78.96f)
                curveToRelative(-4.14f, 10.97f, -22.16f, 16.48f, -35.82f, 16.48f)
                curveToRelative(-13.32f, 0.0f, -22.2f, -5.29f, -26.71f, -15.9f)
                curveToRelative(-4.49f, -10.6f, -6.72f, -34.8f, -6.72f, -72.63f)
                lineToRelative(0.0f, -227.6f)
                curveToRelative(0.0f, -39.24f, 1.97f, -63.7f, 5.93f, -73.44f)
                curveToRelative(3.95f, -9.7f, 12.57f, -14.57f, 25.88f, -14.57f)
                curveToRelative(13.64f, 0.0f, 31.96f, 5.54f, 36.63f, 16.67f)
                curveToRelative(4.67f, 11.14f, 7.01f, 34.91f, 7.01f, 71.31f)
                lineToRelative(0.0f, 220.72f)
                close()
                moveTo(2082.73f, 300.5f)
                lineTo(2082.73f, 1161.71f)
                lineTo(2284.13f, 1161.71f)
                lineTo(2298.07f, 1106.84f)
                curveToRelative(18.21f, 22.06f, 38.3f, 38.61f, 60.27f, 49.63f)
                curveToRelative(21.96f, 11.02f, 54.76f, 16.52f, 80.12f, 16.52f)
                curveToRelative(35.36f, 0.0f, 65.9f, -9.29f, 91.62f, -27.84f)
                curveToRelative(25.71f, -18.56f, 42.06f, -40.51f, 49.04f, -65.8f)
                curveToRelative(6.96f, -25.3f, 10.44f, -63.74f, 10.44f, -115.38f)
                lineToRelative(0.0f, -241.62f)
                curveToRelative(0.0f, -52.0f, -1.17f, -85.94f, -3.48f, -101.85f)
                curveToRelative(-2.34f, -15.91f, -9.19f, -32.18f, -20.61f, -48.8f)
                curveToRelative(-11.43f, -16.6f, -28.02f, -29.52f, -49.81f, -38.72f)
                curveToRelative(-21.78f, -9.22f, -47.46f, -13.81f, -77.09f, -13.81f)
                curveToRelative(-25.71f, 0.0f, -58.66f, 5.12f, -80.62f, 15.27f)
                curveToRelative(-21.96f, 10.15f, -41.86f, 25.55f, -59.71f, 46.2f)
                lineToRelative(0.0f, -280.14f)
                lineToRelative(-215.52f, 0.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFfbfbed)), stroke = null, fillAlpha = 0.3f, strokeAlpha
                = 0.3f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveToRelative(2298.24f, 300.5f)
                lineToRelative(0.0f, 280.14f)
                curveToRelative(17.85f, -20.65f, 37.75f, -36.06f, 59.71f, -46.2f)
                curveToRelative(21.96f, -10.15f, 54.92f, -15.27f, 80.62f, -15.27f)
                curveToRelative(29.63f, 0.0f, 55.31f, 4.6f, 77.09f, 13.81f)
                curveToRelative(21.79f, 9.2f, 38.38f, 22.12f, 49.81f, 38.72f)
                curveToRelative(11.42f, 16.62f, 18.27f, 32.88f, 20.61f, 48.8f)
                curveToRelative(2.31f, 15.91f, 3.48f, 49.85f, 3.48f, 101.85f)
                lineToRelative(0.0f, 241.62f)
                curveToRelative(0.0f, 51.64f, -3.48f, 90.08f, -10.44f, 115.38f)
                curveToRelative(-6.98f, 25.29f, -23.33f, 47.23f, -49.04f, 65.8f)
                curveToRelative(-25.72f, 18.55f, -56.25f, 27.84f, -91.62f, 27.84f)
                curveToRelative(-25.36f, 0.0f, -58.16f, -5.5f, -80.12f, -16.52f)
                curveToRelative(-21.97f, -11.02f, -42.06f, -27.57f, -60.27f, -49.63f)
                lineToRelative(-13.94f, 54.87f)
                lineToRelative(-201.41f, 0.0f)
                lineToRelative(0.0f, -861.22f)
                lineToRelative(215.51f, 0.0f)
                moveToRelative(33.44f, 747.68f)
                curveToRelative(13.66f, 0.0f, 31.67f, -5.51f, 35.82f, -16.48f)
                curveToRelative(4.13f, -10.95f, 6.2f, -37.28f, 6.2f, -78.96f)
                lineToRelative(0.0f, -220.72f)
                curveToRelative(0.0f, -36.39f, -2.34f, -60.17f, -7.01f, -71.31f)
                curveToRelative(-4.68f, -11.14f, -22.99f, -16.67f, -36.63f, -16.67f)
                curveToRelative(-13.3f, 0.0f, -21.93f, 4.88f, -25.88f, 14.57f)
                curveToRelative(-3.96f, 9.74f, -5.93f, 34.2f, -5.93f, 73.44f)
                lineToRelative(0.0f, 227.6f)
                curveToRelative(0.0f, 37.83f, 2.23f, 62.03f, 6.72f, 72.63f)
                curveToRelative(4.51f, 10.61f, 13.39f, 15.9f, 26.71f, 15.9f)
                moveToRelative(-13.7f, -767.42f)
                lineToRelative(-19.73f, 0.0f)
                lineToRelative(-215.51f, 0.0f)
                lineToRelative(-19.73f, 0.0f)
                lineToRelative(0.0f, 19.73f)
                lineToRelative(0.0f, 861.22f)
                lineToRelative(0.0f, 19.73f)
                lineToRelative(19.73f, 0.0f)
                lineToRelative(201.41f, 0.0f)
                lineToRelative(15.35f, 0.0f)
                lineToRelative(3.78f, -14.87f)
                lineToRelative(5.21f, -20.51f)
                curveToRelative(12.92f, 11.45f, 26.64f, 20.83f, 41.03f, 28.05f)
                curveToRelative(27.37f, 13.74f, 65.03f, 18.61f, 88.96f, 18.61f)
                curveToRelative(39.41f, 0.0f, 74.11f, -10.62f, 103.16f, -31.57f)
                curveToRelative(29.33f, -21.18f, 48.35f, -46.93f, 56.52f, -76.55f)
                curveToRelative(7.51f, -27.28f, 11.15f, -66.74f, 11.15f, -120.63f)
                lineToRelative(0.0f, -241.62f)
                curveToRelative(0.0f, -53.37f, -1.21f, -87.61f, -3.69f, -104.69f)
                curveToRelative(-2.78f, -18.91f, -10.81f, -38.12f, -23.87f, -57.14f)
                curveToRelative(-13.57f, -19.71f, -33.21f, -35.09f, -58.4f, -45.72f)
                curveToRelative(-24.09f, -10.2f, -52.61f, -15.37f, -84.76f, -15.37f)
                curveToRelative(-23.96f, 0.0f, -61.61f, 4.47f, -88.9f, 17.08f)
                curveToRelative(-11.01f, 5.09f, -21.59f, 11.38f, -31.7f, 18.85f)
                lineToRelative(0.0f, -234.87f)
                lineToRelative(0.0f, -19.73f)
                lineToRelative(0.0f, 0.0f)
                close()
                moveTo(2331.68f, 1028.44f)
                curveToRelative(-6.9f, 0.0f, -7.7f, -1.89f, -8.55f, -3.88f)
                curveToRelative(-1.2f, -2.84f, -5.15f, -15.95f, -5.15f, -64.91f)
                lineToRelative(0.0f, -227.6f)
                curveToRelative(0.0f, -51.02f, 3.43f, -63.42f, 4.48f, -66.0f)
                curveToRelative(0.37f, -0.9f, 0.6f, -1.03f, 0.84f, -1.17f)
                curveToRelative(0.46f, -0.26f, 2.31f, -1.11f, 6.76f, -1.11f)
                curveToRelative(8.87f, 0.0f, 16.67f, 3.27f, 18.66f, 5.15f)
                curveToRelative(1.74f, 4.61f, 5.24f, 19.38f, 5.24f, 63.1f)
                lineToRelative(0.0f, 220.72f)
                curveToRelative(0.0f, 49.77f, -3.02f, 66.0f, -4.62f, 71.1f)
                curveToRelative(-2.17f, 1.74f, -9.5f, 4.6f, -17.66f, 4.6f)
                lineToRelative(0.0f, 0.0f)
                close()
            }
        }
            .build()
        return _imdbLogo!!
    }

private var _imdbLogo: ImageVector? = null
