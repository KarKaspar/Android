package com.example.animation1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
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
import com.example.animation1.ui.theme.Animation1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Animation1Theme {
                DemoAnimatedVisibility()
            }
        }
    }
}

@Composable
fun SimpleText(
    text: String = "Hello there!",
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

//animate
@Composable
private fun DemoAnimatedVisibility() {
    var isVisible by remember {
        mutableStateOf(true)
    }
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SimpleText()

        AnimatedVisibility(
            visible = isVisible,
            enter = slideInHorizontally() + fadeIn(),
            exit = fadeOut(
                animationSpec = tween(durationMillis = 1000)
            )
        ) {
            SimpleText(text = "Animate", color = Color.Blue, fontSize = 48.sp)
        }
        //SimpleText(text = "Animate", color = Color.Blue, fontSize = 48.sp)

        /*if (isVisible) { // just dissapears, not smooth
            SimpleText(text = "Animate", color = Color.Blue, fontSize = 48.sp)
        }*/

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