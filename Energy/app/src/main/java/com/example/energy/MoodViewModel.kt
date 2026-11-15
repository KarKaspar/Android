package com.example.energy

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MoodViewModel : ViewModel() {
    private var _energy by mutableStateOf(50)
    private var _hourOfDay by mutableStateOf(12)

    // Public state
    var energy: Int
        get() = _energy
        set(value) {
            _energy = value.coerceIn(0, 100)
        }

    var hourOfDay: Int
        get() = _hourOfDay
        set(value) {
            _hourOfDay = value.coerceIn(0, 23)
        }

    // Derived mood state
    val mood: String
        get() = when {
            energy > 80 && isDaytime() -> "🔥 Supercharged"
            energy > 60 -> "😊 Productive"
            energy > 40 -> "😌 Chill"
            energy > 20 -> "😴 Tired"
            else -> "Dead"
        }

    private fun isDaytime(): Boolean {
        return hourOfDay in 6..18
    }

    // Interactions
    fun drinkCoffee() {
        energy += 20
    }

    fun takeNap() {
        energy += 30
        hourOfDay += 1
    }

    fun scrollSocialMedia() {
        energy -= 15
    }
}