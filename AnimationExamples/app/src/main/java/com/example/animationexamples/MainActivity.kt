package com.example.animationexamples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animationexamples.ui.theme.AnimationExamplesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimationExamplesTheme {
                DemoAnimatedVisibility()
            }
        }
    }
}

@Composable
fun SimpleText(
    text: String = "Hello, World!",
    color: Color = Color.Red,
    fontSize: TextUnit = 24.sp
) {
    Text(
        text = text,
        fontSize = fontSize,
        color = color,
        modifier = Modifier.padding(16.dp)
    )
}

// Animated Visibility
@Composable
private fun DemoAnimatedVisibility() {
    var isVisible by remember {
        mutableStateOf(true)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SimpleText()

        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(
                animationSpec = tween(durationMillis = 1000)
            ),
            exit = fadeOut(animationSpec = tween(durationMillis = 2000)) + slideOutVertically(animationSpec = tween(durationMillis = 2000))
        ) {
            SimpleText(text = "Animate Me", color = Color.Blue, fontSize = 48.sp)
        }

        Button(onClick = {
            isVisible = !isVisible
        }) {
            Text("Click")
        }

    }


}

@Preview(showBackground = true)
@Composable
private fun PreviewDemoAnimatedVisibility() {
    DemoAnimatedVisibility()
}








