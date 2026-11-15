package com.example.greetings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun GreetingsTitle() {
    val subtitles = listOf(
        stringResource(R.string.exploring_android_programming),
        stringResource(R.string.learning_how_to_bake),
        stringResource(R.string.programming_recipes),
        stringResource(R.string.a_quest_for_knowledge)
    )
    var subtitle by remember { mutableStateOf(subtitles.first()) }

    Column {
        Text(
            text = stringResource(R.string.greetings),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Thin,
            modifier = Modifier
                .clickable {
                    subtitle = subtitles.random()
                }
        )
    }
}

@Preview
@Composable
fun PreviewGreetingsTitle() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        GreetingsTitle()
    }
}