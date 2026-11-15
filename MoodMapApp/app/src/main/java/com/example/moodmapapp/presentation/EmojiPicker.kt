package com.example.moodmapapp.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moodmapapp.domain.MoodEntry

@Composable
fun EmojiPicker(
    onEmojiSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val rowLength = MoodEntry.emojis.size / 2

    val row1 = MoodEntry.emojis.take(rowLength)
    val row2 = MoodEntry.emojis.drop(rowLength)

    Surface(
        color = Color.Blue.copy(0.2f),
        modifier = Modifier
            .fillMaxSize()
            .clickable { onDismiss() }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Pick your mood",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(Modifier.height(16.dp))

            EmojiRowPicker(
                row = row1,
                onEmojiSelected = onEmojiSelected
            )

            Spacer(Modifier.height(16.dp))

            EmojiRowPicker(
                row = row2,
                onEmojiSelected = onEmojiSelected
            )
        }
    }
}

@Composable
fun EmojiRowPicker(
    row: List<String>,
    onEmojiSelected: (String) -> Unit
) {
    Row {
        row.forEach { emoji ->
            Text(
                emoji,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .padding(8.dp)
                    .padding(horizontal = 8.dp)
                    .clickable {
                        onEmojiSelected(emoji)
                    }
            )
        }
    }
}

@Preview
@Composable
private fun EmojiPickerPreview() {
    EmojiPicker(
        onEmojiSelected = {},
        onDismiss = {}
    )
}