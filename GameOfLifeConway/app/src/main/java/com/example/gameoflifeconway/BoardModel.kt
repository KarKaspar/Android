package com.example.gameoflifeconway

import kotlin.random.Random

class BoardModel(private val gridSize: Int) {
    var creatures: Array<IntArray> = Array(gridSize) { IntArray(gridSize) { Random.nextInt(0, 2) } }

    fun randomBoard() {
        creatures = Array(gridSize) { IntArray(gridSize) { Random.nextInt(0, 2) } }
    }

    fun clearBoard() {
        creatures = Array(gridSize) { IntArray(gridSize) { 0 } }
    }

    fun countLiveNeighbours(row: Int, col: Int): Int {
        val directions = listOf(
            -1 to -1, 0 to -1, 1 to -1,
            -1 to 0,           1 to 0,
            -1 to 1, 0 to 1, 1 to 1
        )

        return directions.sumOf { (dx, dy) ->
            val newRow = (row + dx + gridSize) % gridSize
            val newCol = (col + dy + gridSize) % gridSize
            creatures[newRow][newCol]  // 1 if alive and 0 if not alive
        }
    }

    fun safelyAssignCreatureState(i: Int, j: Int, state: Int) {
        val newRow = (i + gridSize) % gridSize
        val newCol = (j + gridSize) % gridSize
        creatures[newRow][newCol] = state
    }

    fun nextGeneration(): BoardModel {
        val newBoard = this.copy()

        for (row in 0..<gridSize) {
            for (col in 0..<gridSize) {
                val liveNeighbours = countLiveNeighbours(row, col)

                // Apply rules
                if (creatures[row][col] == 1) {  // Cell is alive
                    // Die of loneliness or overcrowded
                    if (liveNeighbours < 2 || liveNeighbours > 3) {
                        newBoard.creatures[row][col] = 0 // Creature dies
                    }
                } else { // Cell is dead
                    if (liveNeighbours == 3) {
                        newBoard.creatures[row][col] = 1 // Creature is born
                    }
                }
            }
        }

        return newBoard
    }

    fun applyDesignPattern(
        row: Int,
        col: Int,
        designType: DesignType,
        swapXY: Boolean,
    ): BoardModel {
        val newBoard = this.copy() // Create a copy of the current board

        if (newBoard.creatures[row][col] == 0) {
            for (offset in designType.offsetDesign) {
                val dx = if (swapXY) offset.first else offset.second
                val dy = if (swapXY) offset.second else offset.first

                newBoard.safelyAssignCreatureState(
                    i = row + dx,
                    j = col + dy,
                    state = 1
                )
            }
        }

        return newBoard
    }

    // Deep copy
    fun copy(): BoardModel {
        val newModel = BoardModel(gridSize)
        newModel.creatures = creatures.map { it.copyOf() }.toTypedArray()
        return newModel
    }
}