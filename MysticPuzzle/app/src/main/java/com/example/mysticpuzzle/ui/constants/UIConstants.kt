package com.example.mysticpuzzle.ui.constants

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object UIConstants {
    val BoardCorner = 24.dp
    val TileCorner = 14.dp
    val BoardPadding = 12.dp

    val GridSize = 4

    val TileSize = 72.dp
    val TileSpacing = 8.dp
    val TileSizeWithSpacing = TileSize + TileSpacing

    val BoardSize =
        UIConstants.TileSize * UIConstants.GridSize +
                UIConstants.TileSpacing * (UIConstants.GridSize - 1) +
                UIConstants.BoardPadding * 2

    val BoardContainerColor = Color(0xFF101319)
    val BoardStrokeColor = Color(0x33FFFFFF)
    val TileColor = Color(0xFF2A2F3A)
    val TileHighlightColor = Color(0x40FFFFFF)
    val ButtonColor = Color(0xFFFFC857)
}