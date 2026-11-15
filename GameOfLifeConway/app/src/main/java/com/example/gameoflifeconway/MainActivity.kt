package com.example.gameoflifeconway

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gameoflifeconway.ui.theme.GameOfLifeConwayTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameOfLifeConwayTheme {
//                Text("Game of Life")
                GameOfLifeView()
            }
        }
    }
}

@Composable
fun GameOfLifeView() {
    val n = 50
    var boardModel by remember { mutableStateOf(BoardModel(gridSize = n)) }
    var selectedDesign by remember { mutableStateOf(DesignType.TUB) }
    var swapXY by remember { mutableStateOf(false) }
    var showImage by remember { mutableStateOf(true) }

    var currentImageResId by remember {
        mutableIntStateOf(R.drawable.autumn3
        )
    }

    var isPlaying by remember {
        mutableStateOf(true)
    }
    var speed by remember {
        mutableFloatStateOf(200.0f) // generations per second
    }
    var showDesignSelectionView by remember {
        mutableStateOf(true)
    }

    // Timer loop
    LaunchedEffect(speed, isPlaying) {
        while (true) {
            delay((1000 / speed).toLong())
            if (isPlaying) {
                boardModel = boardModel.nextGeneration()
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Background()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            TitleView()

            BoardView(
                boardModel = boardModel,
                selectedDesign = selectedDesign,
                swapXY = swapXY,
                currentImage = currentImageResId,
                showImage = showImage,
                gridSize = n,
                color = Color.Red,
                onCellTap = { row, col ->
                    boardModel = boardModel.applyDesignPattern(row, col, selectedDesign, swapXY)
                }
            )

            if (showDesignSelectionView) {
                DesignSelectionView(
                    selectedDesign = selectedDesign,
                    onDesignSelected = { newDesign ->
                        selectedDesign = newDesign
                    },
                    swapXY = swapXY,
                    onSwapXYChanged = {
                        swapXY = !swapXY
                    }
                )
            }

            ControlView(
                speed = speed,
                isPlaying = isPlaying,
                showDesignSelectionView = showDesignSelectionView,
                onTogglePlayPause = { isPlaying = it },
                onRefresh = { boardModel.randomBoard() },
                onClear = { boardModel.clearBoard() },
                onToggleImage = { showImage = !showImage },
                onChangeImage = {
                    currentImageResId = BackgroundImages.all.random()
                },
                onToggleVisibility = { showDesignSelectionView = !showDesignSelectionView },
                onSpeedChange = { speed = it }
            )


        }
    }
}

@Composable
fun TitleView() {
    Text(
        text = "Game of Life",
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = Color.White,   /// Default color
        style = TextStyle(
            brush = Brush.linearGradient(
                colors = listOf(
                    colorResource(id = R.color.gradColor1),
                    colorResource(id = R.color.gradColor2),
                    colorResource(id = R.color.gradColor3)
                )
            )
        )
    )
}



@Composable
fun RoundedButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(15),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
    ) {
        Text(text = text, color = Color.White)
    }
}

@Composable
fun Background() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.bgColor))
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GameOfLifeView()
}
