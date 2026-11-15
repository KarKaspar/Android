package com.example.mysticpuzzle.ui.screen

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mysticpuzzle.ui.component.ActionButton
import com.example.mysticpuzzle.ui.component.GameHeader
import com.example.mysticpuzzle.ui.component.Tile
import com.example.mysticpuzzle.ui.constants.UIConstants
import com.example.mysticpuzzle.viewmodel.TilesViewModel

@Preview
@Composable
private fun PreviewTilesScreen() {
    TilesScreen()
}

@Composable
fun TilesScreen(
    viewModel: TilesViewModel = viewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val isGameOver = viewModel.isGameOver()
    val tiles = state.value.tiles
    val moves = state.value.moves

    val shuffleConstant = 100

    Box(
        modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            GameHeader(
                moves = moves,
                isGameOver = isGameOver
            )

            // Board
            Card(
                shape = RoundedCornerShape(UIConstants.BoardCorner),
                colors = CardDefaults.cardColors(
                    containerColor = UIConstants.BoardContainerColor
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                modifier = Modifier.size(UIConstants.BoardSize)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(UIConstants.BoardPadding)
                ) {
                    // Iterate over tiles
                    tiles.forEach { tile ->
                        // Compute location of tile
                        val targetX = tile.col * UIConstants.TileSizeWithSpacing
                        val targetY = tile.row * UIConstants.TileSizeWithSpacing

                        // Animations
                        val x by animateDpAsState(
                            targetValue = targetX,
                            animationSpec = tween(120),
                            label = "tileX"
                        )

                        val y by animateDpAsState(
                            targetValue = targetY,
                            animationSpec = tween(120),
                            label = "tileY"
                        )

                        // Place the tile on the board
                        Box(
                            modifier = Modifier.offset(x = x, y = y)
                        ) {
                            Tile(
                                value = tile.value,
                                enabled = !isGameOver
                            ) {
                                viewModel.tryMove(tile)
                            }
                        }
                    }
                }
            }

            // Shuffle Button
            ActionButton(
                text = "Shuffle",
                icon = Icons.Default.Refresh,
                contentDescription = "Shuffle Button",
                buttonColor = UIConstants.ButtonColor,
                onClick = { viewModel.reset(times = shuffleConstant) },
                modifier = Modifier.width(160.dp)
            )
        }
    }
}









