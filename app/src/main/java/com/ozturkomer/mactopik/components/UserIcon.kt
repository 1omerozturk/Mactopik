package com.ozturkomer.mactopik.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val UserCircle: ImageVector
    get() {
        if (_UserCircle != null) {
            return _UserCircle!!
        }
        _UserCircle = ImageVector.Builder(
            name = "UserCircle",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = null,
                fillAlpha = 1.0f,
                stroke = SolidColor(Color(0xFF0F172A)),
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(17.9815f, 18.7248f)
                curveTo(16.6121f, 16.9175f, 14.4424f, 15.75f, 12f, 15.75f)
                curveTo(9.5576f, 15.75f, 7.3879f, 16.9175f, 6.0185f, 18.7248f)
                moveTo(17.9815f, 18.7248f)
                curveTo(19.8335f, 17.0763f, 21f, 14.6744f, 21f, 12f)
                curveTo(21f, 7.0294f, 16.9706f, 3f, 12f, 3f)
                curveTo(7.0294f, 3f, 3f, 7.0294f, 3f, 12f)
                curveTo(3f, 14.6744f, 4.1665f, 17.0763f, 6.0185f, 18.7248f)
                moveTo(17.9815f, 18.7248f)
                curveTo(16.3915f, 20.1401f, 14.2962f, 21f, 12f, 21f)
                curveTo(9.7038f, 21f, 7.6085f, 20.1401f, 6.0185f, 18.7248f)
                moveTo(15f, 9.75f)
                curveTo(15f, 11.4069f, 13.6569f, 12.75f, 12f, 12.75f)
                curveTo(10.3431f, 12.75f, 9f, 11.4069f, 9f, 9.75f)
                curveTo(9f, 8.0931f, 10.3431f, 6.75f, 12f, 6.75f)
                curveTo(13.6569f, 6.75f, 15f, 8.0931f, 15f, 9.75f)
                close()
            }
        }.build()
        return _UserCircle!!
    }

private var _UserCircle: ImageVector? = null
