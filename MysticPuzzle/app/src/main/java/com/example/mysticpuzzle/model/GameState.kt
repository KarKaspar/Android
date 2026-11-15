package com.example.mysticpuzzle.model

import com.example.mysticpuzzle.model.TileData

data class GameState(
    val tiles: List<TileData>,
    val moves: Int,
    val emptyRow: Int,
    val emptyCol: Int
)