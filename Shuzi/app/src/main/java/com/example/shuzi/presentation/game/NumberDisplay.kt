package com.example.shuzi.presentation.game

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shuzi.domain.model.OptionModel
import com.example.shuzi.ui.theme.ShuziTheme
import com.example.shuzi.ui.theme.deepChineseRed

@Preview
@Composable
private fun NumberDisplayPreview() {
    ShuziTheme {
        NumberDisplay(
            number = OptionModel.THREE,
            showPinyin = true,
            onPlayAudio = {},
            onTogglePinyin = {}
        )
    }
}

@Composable
fun NumberDisplay(
    number: OptionModel,
    showPinyin: Boolean,
    onPlayAudio: () -> Unit,
    onTogglePinyin: () -> Unit,
    modifier: Modifier = Modifier
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary
    val deepRed = MaterialTheme.colorScheme.deepChineseRed

    val mainGradient = Brush.verticalGradient(
        colors = listOf(
            primaryColor,
            deepRed
        )
    )

    Card(
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .clip(RoundedCornerShape(24.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(mainGradient)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Toggle Pinyin and Speaker
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = onTogglePinyin,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = secondaryColor.copy(alpha = 0.2f),
                            contentColor = secondaryColor
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = if (showPinyin) "隐藏拼音 (Hide)" else "显示拼音 (Show)",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    IconButton(
                        onClick = onPlayAudio,
                        modifier = Modifier
                            .background(secondaryColor, shape = RoundedCornerShape(12.dp))
                            .size(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                            contentDescription = "发音 (Play Audio)",
                            tint = primaryColor
                        )
                    }
                }

                Spacer(Modifier.weight(1f))

                // Chinese number
                Text(
                    text = number.chinese,
                    fontSize = 100.sp,
                    fontWeight = FontWeight.Bold,
                    color = secondaryColor
                )

                // Pinyin
                AnimatedVisibility(
                    visible = showPinyin,
                    enter = fadeIn(animationSpec = tween(350)),
                    exit = fadeOut(animationSpec = tween(250))
                ) {
                    Text(
                        text = number.pinyin,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White.copy(alpha = 0.9f),
                        letterSpacing = 2.sp
                    )
                }

                Spacer(Modifier.weight(1f))

                // Prompt
                Text(
                    text = "这是什么数字? (What number is this?)",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.White.copy(alpha = 0.7f),
                    letterSpacing = 1.sp
                )
            }
        }
    }
}









