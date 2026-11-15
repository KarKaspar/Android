package com.example.geoblur.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geoblur.model.GameUiState
import com.example.geoblur.model.Landmark
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okio.IOException

class GameViewModel : ViewModel() {
    private val initialBlurRadius: Float = 20f
    private val numberOfTries = 2

    private val _uiState = MutableStateFlow(
        GameUiState(blurRadius = initialBlurRadius)
    )

    val uiState = _uiState.asStateFlow()

    private val totalLandmarks: Int get() = _uiState.value.shuffledLandmarks.size

    val currentLandmark: Landmark?
        get() {
            val state = _uiState.value
            val index = state.currentIndex
            val list = state.shuffledLandmarks
            Log.d("LandmarkCheck", "currentIndex = $index, shuffledLandmarks.size = ${list.size}")
            // Safety check - valid index and the list should be non-empty
            return if (list.isNotEmpty() && index in list.indices) list[index] else null
        }

    val playerWon: Boolean
        get() {
            val state = _uiState.value
            return state.currentIndex == state.shuffledLandmarks.size - 1 && state.blurRadius > 0f
        }

    val progress: Float
        get() {
            val state = _uiState.value
            val total = totalLandmarks.coerceAtLeast(1)
            return if (state.showDialog && playerWon) 1f else state.currentIndex.toFloat() / total
        }

    private val json = Json {
        ignoreUnknownKeys = true
    }

    fun loadLandmarksFromAssets(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val jsonString = context.assets.open("landmarks.json")
                    .bufferedReader()
                    .use { it.readText() }

                // Parse JSON string to a Kotlin List
                val landmarks = json.decodeFromString<List<Landmark>>(jsonString)

                // Shuffle the landmarks
                val shuffled = landmarks.shuffled()

                // Update UI State
                _uiState.update {
                    it.copy(
                        shuffledLandmarks = shuffled,
                        currentIndex = 0,
                        blurRadius = initialBlurRadius,
                        showDialog = false,
                        loading = false
                    )
                }

                Log.d("loadLandmarksFromAssets", "Loaded ${landmarks.size} landmarks")
            } catch (e: IOException) {
                Log.e("GameViewModel", "Failed to load landmarks", e)
            } catch (e: Exception) {
                Log.e("GameViewModel", "JSON parsing error", e)
            }
        }
    }

    fun submitAnswer(option: String) {
        // Get current landmark and if null then exit function
        val current = currentLandmark ?: return

        // Is answer correct?
        if (option == current.name) {
            // Proceed to next question
            if (_uiState.value.currentIndex < totalLandmarks - 1) {
                // Move to the next question by updating the uiState
                _uiState.update {
                    it.copy(
                        currentIndex = it.currentIndex + 1,
                        blurRadius = initialBlurRadius
                    )
                }
            } else {
                // It is the last question so show dialog
                _uiState.update { it.copy(showDialog = true) }
            }
        } else {
            val newBlur = (_uiState.value.blurRadius - initialBlurRadius / numberOfTries)
                .coerceAtLeast(0f)

            _uiState.update {
                it.copy(
                    blurRadius = newBlur,
                    showDialog = newBlur == 0f
                )
            }
        }
    }

    fun restartGame() {
        _uiState.update {
            it.copy(
                currentIndex = 0,
                blurRadius = initialBlurRadius,
                showDialog = false,
                shuffledLandmarks = it.shuffledLandmarks.shuffled()
            )
        }
    }
}