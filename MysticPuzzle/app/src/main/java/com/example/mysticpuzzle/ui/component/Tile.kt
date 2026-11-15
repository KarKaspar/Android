package com.example.mysticpuzzle.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mysticpuzzle.ui.constants.UIConstants

@Preview
@Composable
private fun TilePreview() {
    Row {
        Tile(
            value = 3,
            enabled = true
        ) { }
        Tile(
            value = 3,
            enabled = false
        ) { }
    }
}

@Composable
fun Tile(value: Int, enabled: Boolean, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        animationSpec = tween(durationMillis = 120),
        label = "tilePressScale"
    )

    Box(
        modifier = Modifier
            .size(UIConstants.TileSize)
            .scale(scale)
            .clip(shape = RoundedCornerShape(UIConstants.TileCorner))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        UIConstants.TileHighlightColor,
                        UIConstants.TileColor
                    )
                )
            ).border(
                width = 1.dp,
                color = UIConstants.BoardStrokeColor,
                shape = RoundedCornerShape(UIConstants.TileCorner)
            ).clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = String.format("%02d", value),
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.headlineSmall
        )
    }

}











