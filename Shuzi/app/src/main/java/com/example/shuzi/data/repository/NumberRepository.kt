package com.example.shuzi.data.repository

import com.example.shuzi.domain.model.OptionModel

object NumberRepository {
    val allOptions: List<OptionModel> = (1..99)
        .map { num ->
            OptionModel(
                number = num,
                chinese = chineseFor(num),
                pinyin = pinyinFor(num)
            )
        }

    fun randomOption(): OptionModel = allOptions.randomOrNull() ?: OptionModel.ONE

    fun chineseFor(number: Int): String {
        val digits: List<String> = listOf("", "一", "二", "三", "四", "五", "六", "七", "八", "九")
        val ten = "十"
        return when {
            number <= 10 -> if (number==10) ten else digits[number]
            number < 20 -> ten + digits[number % 10] // "十七" 17
            else -> {
                val tens = digits[number / 10]
                val ones = digits[number % 10]
                tens + ten + ones  // "九十七" 97
            }
        }
    }

    fun pinyinFor(number: Int): String {
        val digits: List<String> = listOf("", "yī", "èr", "sān", "sì", "wǔ", "liù", "qī", "bā", "jiǔ")
        val ten = "shí"
        return when {
            number <= 10 -> if (number==10) "shí" else digits[number]
            number < 20 -> ten + " " + digits[number % 10] // "shí qī" 17
            else -> {
                val tens = digits[number / 10]
                val ones = digits[number % 10]
                tens + " " + ten + " " + ones  // "jiǔ shí qī" 97
            }
        }
    }
}