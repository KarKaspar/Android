package com.example.gameoflifeconway

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CreatureGrid(
    creatures: Array<IntArray>,
    gridSize: Int,
    color: Color
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val cellSize = size.width / gridSize
        for (row in 0 until gridSize) {
            for (col in 0 until gridSize) {
                // Is there a creature
                if (creatures[row][col] == 1) {
                    drawRect(
                        color = color,
                        topLeft = Offset(col * cellSize, row * cellSize),
                        size = Size(cellSize, cellSize)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CreatureGridPreview() {
    val n = 50

    val creatures = remember {
        Array(n) { IntArray(n) {
            (0..1).random()
        } }
    }

    Box(
        modifier = Modifier
            .padding(16.dp)
            .background(Color.White)
    ) {
        CreatureGrid(creatures = creatures, gridSize = n, color = Color.Red)
    }
}