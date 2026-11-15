package com.example.shuzi.presentation.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shuzi.presentation.viewmodel.GameViewModel
import com.example.shuzi.ui.theme.ShuziTheme


//@Preview
//@Composable
//private fun GameScreenPreview() {
//    ShuziTheme {
//        GameScreen()
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    viewModel: GameViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var showPinyin by rememberSaveable { mutableStateOf(true) }

    val currentOption = uiState.currentOption

    val totalQuestions = viewModel.totalQuestions

    // Play audio whenever a new number appears
    LaunchedEffect(currentOption) {
        currentOption?.let {
            viewModel.playAudio(it.chinese)
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "数字 Shùzì",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding( horizontal = 20.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            GameProgressBar(
                currentQuestion = uiState.currentQuestion,
                totalQuestions = totalQuestions,
                score = uiState.score
            )

            currentOption?.let { option ->
                NumberDisplay(
                    number = option,
                    showPinyin = showPinyin,
                    onPlayAudio = {
                        viewModel.playAudio(option.chinese)
                    },
                    onTogglePinyin = {
                        showPinyin = !showPinyin
                    }
                )
            }

            QuizGrid(
                options = uiState.options,
                onAnswerSelect = {
                    viewModel.selectOption(it)
                }
            )

            if (uiState.isGameOver) {
                GameOverDialog(
                    score = uiState.score,
                    totalQuestions = totalQuestions,
                    onRestart = {
                        viewModel.resetGame()
                    }
                )
            }
        }
    }
}