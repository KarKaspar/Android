package com.example.shuzi.data.speech

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import com.example.shuzi.domain.model.Language

class SpeechManager(context: Context) : SpeechEngine {
    private var ready = false
    private val tts: TextToSpeech

    init {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                ready = true
                tts.setPitch(pitch)
                tts.setSpeechRate(rate)
            } else {
                Log.e("TTS", "Initialization failed")
            }
        }
    }

    // Exposed properties
    var pitch = 0.7f
        set(value) {
            field = value.coerceIn(0.1f, 2.0f)
            if (ready) tts.setPitch(field)
        }
    var rate = 0.85f
        set(value) {
            field = value.coerceIn(0.1f, 2.0f)
            if (ready) tts.setSpeechRate(field)
        }

    private fun speak(text: String, language: Language) {
        if (!ready) return

        tts.stop()
        tts.language = language.locale
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun speakChinese(text: String) = speak(text, Language.CHINESE)
    override fun speakEnglish(text: String) = speak(text, Language.ENGLISH)

    override fun shutdown() {
        // Release the TextToSpeech engine to free resources.
        tts.shutdown()
    }

}