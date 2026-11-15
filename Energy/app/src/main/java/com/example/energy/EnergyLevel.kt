package com.example.energy

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.energy.ui.theme.EnergyTheme

@Preview
@Composable
private fun EnergyLevelPreview() {
    EnergyTheme {
        Box(
            modifier = Modifier
                .background(Color.Black)
        ) {
            EnergyLevel(energy = 30)
        }
    }
}

@Composable
fun EnergyLevel(energy: Int) {
    val sweep = energy / 100f

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(220.dp)
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            drawCircle(
                color = Color.DarkGray.copy(0.3f),
                style = Stroke(width = 20f)
            )

            drawArc(
                brush = Brush.sweepGradient(
                    listOf(Color(0xFFFFA726), Color(0xFFFF4081))
                ),
                startAngle = -90f,
                sweepAngle = 360 * sweep,
                useCenter = false,
                style = Stroke(width = 20f, cap = StrokeCap.Round)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$energy",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "/ 100",
                color = Color.Gray
            )
        }
    }
}

@Preview
@Composable
private fun dsf() {
    EnergyLevel(60)
}