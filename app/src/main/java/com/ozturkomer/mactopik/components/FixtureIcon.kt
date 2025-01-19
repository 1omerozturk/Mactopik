package com.ozturkomer.mactopik.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val FixtureIcon: ImageVector
    get() {
        if (_Diagram2 != null) {
            return _Diagram2!!
        }
        _Diagram2 = ImageVector.Builder(
            name = "Diagram2",
            defaultWidth = 16.dp,
            defaultHeight = 16.dp,
            viewportWidth = 16f,
            viewportHeight = 16f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(6f, 3.5f)
                arcTo(1.5f, 1.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 7.5f, 2f)
                horizontalLineToRelative(1f)
                arcTo(1.5f, 1.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 10f, 3.5f)
                verticalLineToRelative(1f)
                arcTo(1.5f, 1.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 8.5f, 6f)
                verticalLineToRelative(1f)
                horizontalLineTo(11f)
                arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.5f, 0.5f)
                verticalLineToRelative(1f)
                arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, -1f, 0f)
                verticalLineTo(8f)
                horizontalLineToRelative(-5f)
                verticalLineToRelative(0.5f)
                arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, -1f, 0f)
                verticalLineToRelative(-1f)
                arcTo(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 5f, 7f)
                horizontalLineToRelative(2.5f)
                verticalLineTo(6f)
                arcTo(1.5f, 1.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 6f, 4.5f)
                close()
                moveTo(8.5f, 5f)
                arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.5f, -0.5f)
                verticalLineToRelative(-1f)
                arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.5f, -0.5f)
                horizontalLineToRelative(-1f)
                arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.5f, 0.5f)
                verticalLineToRelative(1f)
                arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.5f, 0.5f)
                close()
                moveTo(3f, 11.5f)
                arcTo(1.5f, 1.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 4.5f, 10f)
                horizontalLineToRelative(1f)
                arcTo(1.5f, 1.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 7f, 11.5f)
                verticalLineToRelative(1f)
                arcTo(1.5f, 1.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 5.5f, 14f)
                horizontalLineToRelative(-1f)
                arcTo(1.5f, 1.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 3f, 12.5f)
                close()
                moveToRelative(1.5f, -0.5f)
                arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.5f, 0.5f)
                verticalLineToRelative(1f)
                arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.5f, 0.5f)
                horizontalLineToRelative(1f)
                arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.5f, -0.5f)
                verticalLineToRelative(-1f)
                arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.5f, -0.5f)
                close()
                moveToRelative(4.5f, 0.5f)
                arcToRelative(1.5f, 1.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 1.5f, -1.5f)
                horizontalLineToRelative(1f)
                arcToRelative(1.5f, 1.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 1.5f, 1.5f)
                verticalLineToRelative(1f)
                arcToRelative(1.5f, 1.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, -1.5f, 1.5f)
                horizontalLineToRelative(-1f)
                arcTo(1.5f, 1.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 9f, 12.5f)
                close()
                moveToRelative(1.5f, -0.5f)
                arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.5f, 0.5f)
                verticalLineToRelative(1f)
                arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.5f, 0.5f)
                horizontalLineToRelative(1f)
                arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.5f, -0.5f)
                verticalLineToRelative(-1f)
                arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.5f, -0.5f)
                close()
            }
        }.build()
        return _Diagram2!!
    }

private var _Diagram2: ImageVector? = null
