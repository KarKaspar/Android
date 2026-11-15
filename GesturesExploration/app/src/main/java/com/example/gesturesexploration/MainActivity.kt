package com.example.gesturesexploration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gesturesexploration.ui.theme.GesturesExplorationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GesturesExplorationTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        MagnifyText()
//        DraggableText()
        SmoothDraggableText()
    }
}


@Composable
fun MagnifyText(modifier: Modifier = Modifier) {
    var scaleFactor by remember { mutableFloatStateOf(1f) }

    val animatedScale by animateFloatAsState(
        targetValue = scaleFactor,
        animationSpec = spring(stiffness = Spring.StiffnessLow),
        label = "Zoom animation"
    )

    Box(
        modifier = Modifier
            .scale(animatedScale)
            .background(Color.Black, shape = RoundedCornerShape(16.dp))
            .pointerInput(Unit) {
                detectTransformGestures(
                    onGesture = { _, _, zoom, _ ->
                        // Accumulate zoom factor
                        scaleFactor *= zoom
                    }
                )
            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        scaleFactor = 1f
                    }
                )
            }
    ) {
        Text(
            text = "Hello, World!",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Cyan,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun DraggableText(modifier: Modifier = Modifier) {
    var offset by remember { mutableStateOf(Offset.Zero) }

    val animatedOffset by animateOffsetAsState(
        targetValue = offset,
        animationSpec = spring(stiffness = Spring.StiffnessLow)
    )

    Box(
        modifier = Modifier
            .offset { IntOffset(offset.x.toInt(), offset.y.toInt()) }
            .background(Color.Black, shape = RoundedCornerShape(16.dp))
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        // Consume gesture to avoid conflicts
                        change.consume()
                        offset = Offset(offset.x + dragAmount.x, offset.y + dragAmount.y)
                    },
                    onDragEnd = {
                        offset = Offset.Zero
                    }
                )
            }

    ) {
        Text(
            text = "Hello, World!",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Cyan,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun SmoothDraggableText(modifier: Modifier = Modifier) {
    val offsetX = remember { androidx.compose.animation.core.Animatable(0f) }
    val offsetY = remember { androidx.compose.animation.core.Animatable(0f) }

    Box(
        modifier = Modifier
            .offset {
                IntOffset(
                    offsetX.value.toInt(),
                    offsetY.value.toInt()
                )
            }
            .background(Color.Black, shape = RoundedCornerShape(16.dp))
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        // Consume gesture to avoid conflicts
                        change.consume()
//                        offsetX.snapTo(offsetX.value + dragAmount.x)
//                        offsetY.snapTo(offsetY.value + dragAmount.y)
                    },
//                    onDragEnd = {
//                        offset = Offset.Zero
//                    }
                )
            }

    ) {
        Text(
            text = "Hello, World!",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Red,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Main()
}