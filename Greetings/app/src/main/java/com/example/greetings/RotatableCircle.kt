package com.example.greetings

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun RotatableCircle() {
    val colors = listOf(
        Color(0xFFCF1855),
        Color(0xFF6534C1),
        Color(0xFF2A92E2),
        Color(0xFFF59129),
        Color(0xFFEAC87D)
    )

    val brush = Brush.sweepGradient(
        colors = colors
    )

    var isRotated by remember { mutableStateOf(false) }
    val rotationAnimation by animateFloatAsState(if (isRotated) 360f else 0f)

    Canvas(
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .clickable { isRotated = !isRotated }
            .rotate(rotationAnimation)
    ) {
        drawCircle(
            brush = brush,
            radius = size.minDimension / 2,
            style = Stroke(width = size.minDimension * 0.35f)
        )
    }

}
