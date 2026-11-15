package com.example.shuzi.domain.model

import java.util.Locale

enum class Language(val locale: Locale) {
    CHINESE(Locale.SIMPLIFIED_CHINESE),
    ENGLISH(Locale.US)
}