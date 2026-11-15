package com.example.shuzi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.shuzi.data.repository.NumberRepository
import com.example.shuzi.data.speech.SpeechEngine
import com.example.shuzi.domain.model.OptionModel
import com.example.shuzi.presentation.game.GameUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class GameViewModel(
    private val speechEngine: SpeechEngine,
    val totalQuestions: Int = 10
) : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    // Publicly exposed state - read only version of the state
    // Reacts to changes by the UI (Compose)
    val uiState: StateFlow<GameUiState> = _uiState

    init {
        startGame()
    }

    private fun startGame() {
        val option = NumberRepository.randomOption()

        _uiState.value = GameUiState(
            currentOption = option,
            options = generateOptions(correct = option)
        )
    }

    private fun generateOptions(correct: OptionModel): List<OptionModel> {
        val wrong = mutableSetOf<OptionModel>()

        while (wrong.size < 3) {
            val random: OptionModel = NumberRepository.randomOption()

            // random is a new answer which does not coincide with correct
            // then add random to the wrong list.
            if (random != correct) wrong.add(random)
        }

        return (wrong + correct).shuffled()
    }

    fun selectOption(selected: OptionModel) {
        val state = _uiState.value
        val correct = state.currentOption
        var newScore = state.score

        // Check if answer is correct
        if (selected == correct) newScore++

        // Is the game over?
        if (state.currentQuestion >= totalQuestions) {
            // Game over
            _uiState.update {
                it.copy(
                    score = newScore,
                    isGameOver = true
                )
            }

        } else {
            // Game is not over
            val next = NumberRepository.randomOption()

            _uiState.update {
                it.copy(
                    score = newScore,
                    currentQuestion = it.currentQuestion + 1,
                    currentOption = next,
                    options = generateOptions(next)
                )
            }
        }
    }

    fun resetGame() {
        startGame()
    }

    fun playAudio(text: String) {
        speechEngine.speakChinese(text)
    }

    override fun onCleared() {
        speechEngine.shutdown()
    }
}