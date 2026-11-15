package com.example.mysticpuzzle.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mysticpuzzle.model.GameState
import com.example.mysticpuzzle.model.TileData
import com.example.mysticpuzzle.ui.constants.UIConstants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.abs

class TilesViewModel(
    private val size: Int = UIConstants.GridSize
) : ViewModel() {
    // State variables
    private val _state = MutableStateFlow(
        value = GameState(
            tiles = emptyList(),
            moves = 0,
            emptyRow = size - 1,
            emptyCol = size - 1
        )
    )

    val state: StateFlow<GameState> = _state.asStateFlow()

    private val solvedGrid: Array<Int?> = Array(size * size) { i ->
        if (i == size*size-1) null else i + 1
    }

    val initialGameShuffle = 220

    // Initializer
    init {
        reset(times = initialGameShuffle)
    }

    fun reset(times: Int) {
        loadTiles(initialTiles = solvedGrid)
        shuffle(times = times)
    }

    private fun index(row: Int, col: Int) = row * size + col

    private fun isAdjacent(r1: Int, c1: Int, r2: Int, c2: Int): Boolean =
        abs(r1 - r2) + abs(c1 - c2) == 1

    fun isGameOver(): Boolean {
        val solved = solvedGrid
        val tiles = _state.value.tiles

        return tiles.all { tile ->
            solved[index(tile.row, tile.col)] == tile.value
        }
    }

    private fun shuffle(times: Int) {
        repeat(times) {
            _state.update { current ->
                val er = current.emptyRow
                val ec = current.emptyCol

                // Get neighbours of empty tile
                val neighbours = current.tiles.filter {
                    isAdjacent(it.row, it.col, er, ec )
                }

                if (neighbours.isEmpty()) return@update current

                val chosen = neighbours.random()

                val updatedTiles = current.tiles.map {
                    if (it.value == chosen.value) {
                        it.copy(row = er, col = ec)
                    } else {
                        it
                    }
                }

                current.copy(
                    tiles = updatedTiles,
                    emptyRow = chosen.row,
                    emptyCol = chosen.col
                )
            }
        }

        _state.update { it.copy(moves = 0) }
    }

    private fun loadTiles(initialTiles: Array<Int?>) {
        val newTiles = initialTiles.mapIndexedNotNull { index, value ->
            value?.let {
                // index = 10
                // size = 4
                // row = 10 / 4 = 2
                // col = 10 % 4 = 2

                // index = 9
                // size = 4
                // row = 9 / 4 = 2
                // col = 9 % 4 = 1

                // index = 8
                // size = 4
                // row = 8 / 4 = 2
                // col = 8 % 4 = 0

                // index = 7
                // size = 4
                // row = 7 / 4 = 1
                // col = 7 % 4 = 3
                TileData(
                    value = it,
                    row = index / size,
                    col = index % size
                )
            }
        }

        _state.value = GameState(
            tiles = newTiles,
            moves = 0,
            emptyRow = size - 1,
            emptyCol = size - 1
        )
    }

    fun tryMove(tile: TileData) {
        _state.update { current ->
            if (isGameOver()) return@update current

            val er = current.emptyRow
            val ec = current.emptyCol

            if (!isAdjacent(tile.row, tile.col, er, ec)) return@update current

            val updatedTiles = current.tiles.map {
                if (it.value == tile.value) {
                    it.copy(row = er, col = ec)
                } else {
                    it
                }
            }

            current.copy(
                tiles = updatedTiles,
                emptyRow = tile.row,
                emptyCol = tile.col,
                moves = current.moves + 1,
            )
        }
    }
}