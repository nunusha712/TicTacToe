package com.example.ttt

class TicTacToeGame {
    var activePlayer = "X"
    var gameState = arrayOf("", "", "", "", "", "", "", "", "")
    var isGameActive = true

    private val winningPositions = arrayOf(
        intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8), intArrayOf(2, 4, 6)
    )

    fun makeMove(index: Int): Boolean {
        if (gameState[index] == "" && isGameActive) {
            gameState[index] = activePlayer
            return true
        }
        return false
    }

    fun checkGameState(): String {
        for (winPos in winningPositions) {
            if (gameState[winPos[0]] != "" &&
                gameState[winPos[0]] == gameState[winPos[1]] &&
                gameState[winPos[1]] == gameState[winPos[2]]) {

                isGameActive = false
                return "WIN"
            }
        }

        if (gameState.none { it == "" }) {
            isGameActive = false
            return "DRAW"
        }

        return "CONTINUE"
    }

    fun switchPlayer() {
        activePlayer = if (activePlayer == "X") "O" else "X"
    }

    fun reset() {
        activePlayer = "X"
        isGameActive = true
        gameState = arrayOf("", "", "", "", "", "", "", "", "")
    }
}