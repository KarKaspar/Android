package com.example.greetings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun MyTextView(text: String, color: Color, alpha: Float = 0.75f) {
    val shape = RoundedCornerShape(20.dp)

    var newColor by remember { mutableStateOf(color) }

    val colors = listOf(
        Color.Red,
        Color.Green,
        Color.Blue,
        Color(red = 1f, green = 0.65f, blue = 0f),
        Color(red = 0.224f, green = 0.318f, blue = 0.808f, alpha = 1.0f),
        Color(red = 0.0f, green = 1.0f, blue = 0.067f, alpha = 1.0f),
        Color(red = 0.071f, green = 0.631f, blue = 0.612f, alpha = 1.0f),
        Color(red = 0.627f, green = 0.125f, blue = 0.941f, alpha = 1.0f),
    )

    Text(
        text = text,
        color = Color.White,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier
            .padding(5.dp)
            .background(newColor.copy(alpha = alpha), shape = shape)
            .padding(16.dp)
            .clickable {
                newColor = colors.random()
            }
    )
}

@Preview
@Composable
fun PreviewTextView() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        MyTextView(text = "Hello there!", color = Color.Green)
        MyTextView(text = "How are you!", color = Color.Blue)
        MyTextView(text = "Peace", color = Color.Red)
    }
}