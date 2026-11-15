package com.example.mysticpuzzle.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
private fun GameHeaderPreview() {
    GameHeader(
        moves = 5,
        isGameOver = false
    )
}

@Preview
@Composable
private fun GameHeaderPreviewIsOver() {
    GameHeader(
        moves = 5,
        isGameOver = true
    )
}
@Composable
fun GameHeader(moves: Int, isGameOver: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "MOVES",
                color = Color.LightGray,
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = moves.toString(),
                color = Color(0xFFFFC107),
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.headlineMedium
            )
        }

        if (isGameOver) {
            Spacer(modifier = Modifier.width(24.dp))

            Text(
                text = "You Won!",
                color = Color.LightGray,
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}