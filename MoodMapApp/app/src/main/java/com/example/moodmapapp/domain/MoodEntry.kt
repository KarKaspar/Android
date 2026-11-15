package com.example.moodmapapp.domain

import kotlin.collections.listOf

data class MoodEntry(
    val emoji: String,
    val description: String,
    val id: Int
) {
    companion object {

        val DEFAULT_MOOD_VALUE = 3

        val sampleMoods = listOf(
            MoodEntry("😎", "Feeling confident and cool!", id = 1),
            MoodEntry("😐", "Just an average day.", id = 2),
            MoodEntry("😔", "A little down today.", id = 3),
            MoodEntry("😎", "Everything’s going smoothly!", id = 4),
            MoodEntry("🤗", "Feeling warm and friendly!", id = 5),
            MoodEntry("😄", "Excited and cheerful!", id = 6),
            MoodEntry("😊", "Calm and content.", id = 7),
            MoodEntry("🤯", "Mind blown, lots happening!", id = 8),
            MoodEntry("🤯", "Overwhelmed but managing.", id = 9),
            MoodEntry("😍", "Feeling love and happiness!", id = 10),
            MoodEntry("😄", "Joyful and energetic!", id = 11),
            MoodEntry("😐", "Neutral, nothing special.", id = 12),
            MoodEntry("🤗", "Sharing good vibes!", id = 13)
        )

        val emojis = listOf("😊", "😄", "😐", "😔", "🤯", "😍", "😎", "🤗")

        val moodValues = mapOf(
            "😊" to 4, "😄" to 5, "😐" to 3, "😔" to 2,
            "🤯" to 1, "😍" to 5, "😎" to 4, "🤗" to 4
        )

        fun generateMoodMessage(emoji: String): String {
            return when (moodValues[emoji]) {
                1 -> listOf(
                    "Not great today...",
                    "Feeling pretty low.",
                    "It's a rough one."
                ).random()
                2 -> listOf(
                    "Been better but hanging on.",
                    "A slow day.",
                    "Feeling a little tired."
                ).random()
                3 -> listOf(
                    "Just an okay moment.",
                    "Calm and neutral.",
                    "Nothing special, nothing bad."
                ).random()
                4 -> listOf(
                    "Good vibes!",
                    "Feeling nice today.",
                    "Pretty happy right now."
                ).random()
                5 -> listOf(
                    "Amazing!",
                    "The best mood!",
                    "Feeling fantastic!"
                ).random()
                else -> "Just Chillin."
            }
        }
    }
}