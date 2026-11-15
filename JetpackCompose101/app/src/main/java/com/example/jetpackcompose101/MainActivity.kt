package com.example.jetpackcompose101

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose101.ui.theme.JetpackCompose101Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp()
        }
    }
}

@Composable
fun GradientBackgroundDemo() {

    //animate
    var targetAlpha by remember { mutableStateOf(0f) }
    val animatedAlpha by animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = tween(durationMillis = 10000)
    )

    LaunchedEffect(Unit) {
        targetAlpha = 1f
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                createGradient(alpha = animatedAlpha)
            ),
        contentAlignment =  Alignment.Center
    ) {
        Text("HELLO ME",
            color = Color.White,
            fontSize = 36.sp
        )
    }
}

@Composable
private fun createGradient(alpha: Float = 0.7f) = Brush.linearGradient(
    colors = listOf(Color.Red.copy(alpha = alpha), Color.Black.copy(alpha = alpha), Color.Blue.copy(alpha= alpha)),
    start = Offset.Zero,
    end = Offset.Infinite
    //start = Offset(0f, 0f),
    //end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
)

@Composable
fun MyApp() {
    GradientBackgroundDemo()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApp()
}