package com.example.gameoflifeconway

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DesignButton(
    design: DesignType,
    color: Color,
    isSelected: Boolean,
    onClick: () -> Unit
    ) {
    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1.05f else 0.9f
    )

    Box(
        modifier = Modifier
            .scale(scale)
            .clip(RoundedCornerShape(15.dp))
            .background(
                color.copy(alpha = if (isSelected) 1f else 0.8f)
            )
            .border(
                width = if (isSelected) 2.dp else 0.dp,
                color = Color.Black,
                shape = RoundedCornerShape(15.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Text(
            text = design.name,
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview
@Composable
private fun DesignButtonPreview() {
    val customPurple = Color(0xFF9c27B0)
    val colors: List<Color> = listOf(Color.Red, Color.Blue, customPurple)

    Column {
        for (color in colors) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                DesignButton(
                    design = DesignType.TUB,
                    color = color,
                    isSelected = false,
                ) { }

                DesignButton(
                    design = DesignType.TUB,
                    color = color,
                    isSelected = true,
                ) { }
            }
        }
    }
}