package com.example.geoblur.model

import android.util.Log
import kotlinx.serialization.Serializable

@Serializable
data class Landmark(
    val name: String,
    val images: List<String>,
    val options: List<String>
)

data class GameUiState(
    val shuffledLandmarks: List<Landmark> = emptyList(),
    val currentIndex: Int = 0,
    val blurRadius: Float = 0f,
    val showDialog: Boolean = false,
    val loading: Boolean = true,

    // Derived UI State
    val currentLandmark: Landmark? = null,
    val progress: Float = 0f,
    val playerWon: Boolean = false
)













