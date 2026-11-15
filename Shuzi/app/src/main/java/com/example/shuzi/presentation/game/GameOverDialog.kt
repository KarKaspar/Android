package com.example.shuzi.presentation.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shuzi.ui.theme.ShuziTheme

@Preview
@Composable
private fun GameOverDialogPreview() {
    ShuziTheme {
        GameOverDialog(
            score = 8,
            totalQuestions = 10,
            onRestart = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameOverDialog(
    score: Int,
    totalQuestions: Int,
    onRestart: () -> Unit
) {
    var primaryColor = MaterialTheme.colorScheme.primary
    var secondaryColor = MaterialTheme.colorScheme.secondary

    BasicAlertDialog(
        onDismissRequest = {}
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title
                Text(
                    text = "游戏结束\nGame Over",
                    color = primaryColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Congrats
                Text(
                    text = "恭喜! (Congratulations!)",
                    color = Color.DarkGray,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Score
                Text(
                    text = "得分 (Score): $score / $totalQuestions",
                    color = primaryColor,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Button - Play again
                Button(
                    onClick = onRestart,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = primaryColor,
                        contentColor = secondaryColor
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "再玩一次 (Play Again)",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}











