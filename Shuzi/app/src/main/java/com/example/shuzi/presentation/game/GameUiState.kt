package com.example.shuzi.presentation.game

import com.example.shuzi.domain.model.OptionModel

data class GameUiState(
    val currentOption: OptionModel? = null,
    val options: List<OptionModel> = emptyList(),
    val currentQuestion: Int = 1,
    val score: Int = 0,
    val isGameOver: Boolean = false
)



//data class GameUiState(
//    val currentOption: OptionModel? = OptionModel.FOUR,
//    val options: List<OptionModel> = listOf<OptionModel>(
//        OptionModel.ONE,
//        OptionModel.TWO,
//        OptionModel.THREE,
//        OptionModel.FOUR,
//    ),
//    val currentQuestion: Int = 1,
//    val score: Int = 0,
//    val isGameOver: Boolean = false
//)