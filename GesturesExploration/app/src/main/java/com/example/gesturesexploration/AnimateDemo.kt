package com.example.gesturesexploration

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SimpleText(modifier: Modifier = Modifier, text: String = "Hello, World!", color: Color = Color.Red) {
    Text(
        text = text,
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold,
        color = color,
        modifier = Modifier.padding(16.dp)
    )
}

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
            enter = slideInHorizontally() + fadeIn(),
            exit = fadeOut()
        ) {
            SimpleText(text = "Animate Me", color = Color.Blue)
        }

        Button(onClick = {
            isVisible = !isVisible
        }) {
            Text("Click")
        }
    }
}

@Composable
fun DemoAnimateProperties(modifier: Modifier = Modifier) {
//    var color: Color = Color.Magenta
    var isFirstColor by remember { mutableStateOf(true) }

    val color by animateColorAsState(
        targetValue = if (isFirstColor) Color.Red else Color.Black
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SimpleText(text = "Color", color = color)

        ElevatedButton(onClick = {
            isFirstColor = !isFirstColor
        }) {
            Text(if (isFirstColor) { "First" } else { "Second" })
        }

    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewAnimations() {
//    DemoAnimatedVisibility()
    DemoAnimateProperties()
}

