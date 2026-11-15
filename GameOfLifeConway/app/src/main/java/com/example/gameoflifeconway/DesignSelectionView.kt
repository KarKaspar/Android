package com.example.gameoflifeconway

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DesignSelectionView(
    selectedDesign: DesignType,
    onDesignSelected: (DesignType) -> Unit,
    swapXY: Boolean,
    onSwapXYChanged: (Boolean) -> Unit
)  {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        val rotationAngle by animateFloatAsState(
            targetValue = if (swapXY) 0f else 90f,
            animationSpec = spring(stiffness = 200f, dampingRatio = 0.7f)
        )

        CircleButton(
            onClick = {
                onSwapXYChanged(swapXY)
            },
            modifier = Modifier
                .graphicsLayer {
                    rotationZ = rotationAngle
                }
        )

        // Design Buttons
        LazyRow(
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(DesignType.values()) { design ->
                DesignButton(
                    design = design,
                    color = getColorForDesign(design),
                    isSelected = selectedDesign == design
                ) {
                    onDesignSelected(design)
                }
            }
        }
    }
}

@Composable
fun CircleButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(modifier = modifier.padding(horizontal = 10.dp)) {
        Button(
            onClick = onClick,
            shape = CircleShape,
            modifier = Modifier.size(40.dp),
            contentPadding = PaddingValues(1.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Arrow Up",
                    modifier = Modifier.size(12.dp)
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Arrow Down",
                    modifier = Modifier.size(12.dp)
                )
            }
        }
    }
}

fun getColorForDesign(designType: DesignType): Color {
    val customPurple = Color(0xff9c27b0)
    return when {
        DesignType.stillLifeDesigns.contains(designType) -> Color.Blue
        DesignType.oscillatorsDesigns.contains(designType) -> customPurple
        else -> Color.Red
    }
}

@Preview
@Composable
private fun PreviewDesignSelectionView() {

    var swapXY by remember {
        mutableStateOf(false)
    }

    Column {
        DesignSelectionView(
            selectedDesign = DesignType.TUB,
            onDesignSelected = {},
            swapXY = swapXY,
            onSwapXYChanged = { swapXY = !swapXY }
        )

        DesignSelectionView(
            selectedDesign = DesignType.TUB,
            onDesignSelected = {},
            swapXY = true,
            onSwapXYChanged = {}
        )


        DesignSelectionView(
            selectedDesign = DesignType.TUB,
            onDesignSelected = {},
            swapXY = false,
            onSwapXYChanged = {}
        )
    }
    
}
