package com.example.shuzi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.shuzi.data.speech.SpeechManager
import com.example.shuzi.presentation.game.GameScreen
import com.example.shuzi.presentation.viewmodel.GameViewModel
import com.example.shuzi.ui.theme.ShuziTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Create Speech engine
        val speechEngine = SpeechManager(this)

        // Create the view model
        val viewModel = GameViewModel(
            speechEngine = speechEngine
        )

        setContent {
            ShuziTheme {
                GameScreen(
                    viewModel = viewModel
                )
            }
        }
    }
}
