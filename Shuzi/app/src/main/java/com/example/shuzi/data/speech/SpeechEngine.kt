package com.example.shuzi.data.speech

interface SpeechEngine {
    fun speakChinese(text: String)
    fun speakEnglish(text: String)
    fun shutdown()
}