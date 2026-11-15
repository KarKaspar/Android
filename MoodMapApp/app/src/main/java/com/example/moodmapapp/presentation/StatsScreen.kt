package com.example.moodmapapp.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moodmapapp.domain.MoodEntry


@Composable
fun StatsScreen(
    blur: Boolean,
    moodEntries: List<MoodEntry>
) {
    val blurValue = if (blur) 10.dp else 0.dp
    val counts = moodEntries.groupingBy { it.emoji }.eachCount()

    val averageMood = if (moodEntries.isNotEmpty()) {
        moodEntries.map { MoodEntry.moodValues[it.emoji] ?: MoodEntry.DEFAULT_MOOD_VALUE }.average()
    } else 0.0

    val emojiOrder = MoodEntry.moodValues.keys.toList()

    val colors = listOf(
        Color(0xFF2196F3),
        Color(0xFFFFC107),
        Color(0xFF4CAF50),
        Color(0xFFF44336),
        Color(0xFF9C27B0),
        Color(0xFF00BCD4),
        Color(0xFFFF5722),
        Color(0xFF8BC34A)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .blur(blurValue)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            "MOOD Stats",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Average Mood: %.1f".format(averageMood),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (moodEntries.isEmpty()) {
            Text("Add some moods to see stats!")
        } else {
            MoodBarChart(
                counts = counts,
                emojiOrder = emojiOrder,
                colors = colors
            )

            Spacer(modifier = Modifier.height(32.dp))

            MoodPieChart(
                counts = counts,
                emojiOrder = emojiOrder,
                colors = colors
            )
        }
    }


}

@Composable
fun MoodBarChart(counts: Map<String, Int>, emojiOrder: List<String>, colors: List<Color>) {
    Column {
        emojiOrder.forEachIndexed { index, emoji ->
            val count = counts[emoji] ?: 0
            val BAR_UNIT = 30

            if (count > 0) {
                val barWidth = count * BAR_UNIT
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(
                        emoji,
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Spacer(Modifier.width(8.dp))

                    Box(modifier = Modifier
                        .height(24.dp)
                        .width(barWidth.dp)
                        .background(colors[index % colors.size])
                    )

                    Spacer(Modifier.width(8.dp))

                    Text("$count")
                }
            }
        }
    }
}

@Composable
fun MoodPieChart(counts: Map<String, Int>, emojiOrder: List<String>, colors: List<Color>) {
    var total = counts.values.sum()
    if (total <= 0) {
        total = 1
    }

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        var startAngle = 0f
        emojiOrder.forEachIndexed { index, emoji ->
            val count: Int = counts[emoji] ?: 0
            val sweep = (count / total.toFloat()) * 360

            drawArc(
                color = colors[index % colors.size],
                startAngle = startAngle,
                sweepAngle = sweep,
                useCenter = true
            )

            startAngle += sweep
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StatsScreenPreview() {
    StatsScreen(
        blur = false,
        moodEntries = MoodEntry.sampleMoods
    )
}