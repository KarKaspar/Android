package com.example.shuzi.domain.model

import java.util.UUID

data class OptionModel(
    val id: String = UUID.randomUUID().toString(),

    val number: Int,
    val chinese: String,
    val pinyin: String
) {
    companion object {
        val ONE = OptionModel(
            number = 1,
            chinese = "一",
            pinyin = "yī"
        )

        val TWO = OptionModel(
            number = 2,
            chinese = "二",
            pinyin = "èr"
        )

        val THREE = OptionModel(
            number = 3,
            chinese = "三",
            pinyin = "sān"
        )

        val FOUR = OptionModel(
            number = 4,
            chinese = "四",
            pinyin = "sì"
        )
    }
}