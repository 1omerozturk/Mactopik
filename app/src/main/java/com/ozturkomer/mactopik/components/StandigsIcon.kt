package com.ozturkomer.mactopik.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Leaderboard: ImageVector
    get() {
        if (_Leaderboard != null) {
            return _Leaderboard!!
        }
        _Leaderboard = ImageVector.Builder(
            name = "Leaderboard",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(160f, 760f)
                horizontalLineToRelative(160f)
                verticalLineToRelative(-320f)
                horizontalLineTo(160f)
                close()
                moveToRelative(240f, 0f)
                horizontalLineToRelative(160f)
                verticalLineToRelative(-560f)
                horizontalLineTo(400f)
                close()
                moveToRelative(240f, 0f)
                horizontalLineToRelative(160f)
                verticalLineToRelative(-240f)
                horizontalLineTo(640f)
                close()
                moveTo(80f, 840f)
                verticalLineToRelative(-480f)
                horizontalLineToRelative(240f)
                verticalLineToRelative(-240f)
                horizontalLineToRelative(320f)
                verticalLineToRelative(320f)
                horizontalLineToRelative(240f)
                verticalLineToRelative(400f)
                close()
            }
        }.build()
        return _Leaderboard!!
    }

public var _Leaderboard: ImageVector? = null
