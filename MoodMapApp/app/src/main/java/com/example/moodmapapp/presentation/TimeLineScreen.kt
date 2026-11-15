package com.example.moodmapapp.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moodmapapp.domain.MoodEntry

@Composable
fun TimeLineScreen(
    blur: Boolean,
    moodEntries: List<MoodEntry>,
    onDelete: (MoodEntry) -> Unit
) {
    val blurValue = if (blur) 10.dp else 0.dp

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .blur(blurValue),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(moodEntries) { entry ->
            MoodCard(entry, onDelete)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TimeLineScreenPreview() {
    TimeLineScreen(
        blur = false,
        moodEntries = MoodEntry.sampleMoods,
        onDelete = {}
    )
}