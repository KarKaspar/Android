package com.example.gameoflifeconway

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min

@Composable
fun BoardView(
    boardModel: BoardModel,
    selectedDesign: DesignType,
    swapXY: Boolean,
    currentImage: Int,
    showImage: Boolean,
    gridSize: Int,
    color: Color,
    onCellTap: (row: Int, col: Int) -> Unit
) {
    BoxWithConstraints {
        val canvasSize: Dp = min(maxWidth, maxHeight)
        val cellSize: Dp = canvasSize / gridSize

        val density: Density = LocalDensity.current // Get density
        val cellSizePx: Float = with(density) { cellSize.toPx() } // Convert dp to px


        Box(
            modifier = Modifier
                .size(canvasSize)
                .clip(RoundedCornerShape(10.dp))
                .border(1.dp, Color.Blue, RoundedCornerShape(10.dp))
                .shadow(3.dp)
                .pointerInput(Unit) {
                    detectTapGestures { tapOffset ->
                        val row = (tapOffset.y / cellSizePx).toInt()
                        val col = (tapOffset.x / cellSizePx).toInt()

                        Log.d("BoardView", "Tapped on row: $row, col: $col")

                        // Delegate to caller/parent
                        onCellTap(row, col)
                    }
                }
        )
        {
            if (showImage) {
                Image(
                    painter = painterResource(id = currentImage),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

            CreatureGrid(
                creatures = boardModel.creatures,
                gridSize = gridSize,
                color = color
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BoardViewPreview() {
    BoardView(
        boardModel = BoardModel(50),
        selectedDesign = DesignType.TUB,
        swapXY = false,
        currentImage = R.drawable.mountain2,
        showImage = false,
        gridSize = 50,
        color = Color.Red,
        onCellTap = { row, col -> }
    )
}