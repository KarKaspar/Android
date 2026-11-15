package com.example.shuzi.presentation.game

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shuzi.domain.model.OptionModel
import com.example.shuzi.ui.theme.ShuziTheme

@Preview
@Composable
private fun QuizGridPreview() {
    ShuziTheme {
        QuizGrid(
            options = listOf<OptionModel>(
                OptionModel.ONE,
                OptionModel.TWO,
                OptionModel.THREE,
                OptionModel.FOUR,
            ),
            onAnswerSelect = {}
        )
    }
}

@Composable
fun QuizGrid(
    options: List<OptionModel>,
    onAnswerSelect: (OptionModel) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(options) { option ->
            QuizOptionCard(
                option = option,
                onClick = { onAnswerSelect(option) }
            )
        }
    }
}

@Composable
fun QuizOptionCard(option: OptionModel, onClick: () -> Unit) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary

    val gradient = Brush.linearGradient(
        colors = listOf(
            primaryColor,
            primaryColor.copy(alpha = 0.55f)
        )
    )

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = option.number.toString(),
                fontSize = 80.sp,
                fontWeight = FontWeight.Bold,
                color = secondaryColor
            )
        }
    }
}










