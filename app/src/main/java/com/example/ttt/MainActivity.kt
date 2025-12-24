package com.example.ttt

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val game = TicTacToeGame()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val statusText = findViewById<TextView>(R.id.statusTextView)
        val playAgainBtn = findViewById<Button>(R.id.playAgainButton)

        val buttons = Array(9) { i ->
            findViewById<Button>(resources.getIdentifier("btn$i", "id", packageName))
        }

        for (i in 0..8) {
            buttons[i].setOnClickListener {
                if (game.makeMove(i)) {
                    buttons[i].text = game.activePlayer
                    handleGameResult(statusText, playAgainBtn)
                }
            }
        }

        playAgainBtn.setOnClickListener {
            game.reset()
            resetUI(buttons, statusText, playAgainBtn)
        }
    }

    private fun handleGameResult(statusText: TextView, playAgainBtn: Button) {
        val result = game.checkGameState()

        when (result) {
            "WIN" -> {
                statusText.text = "Player ${game.activePlayer} Wins!"
                statusText.setTextColor(Color.WHITE)
                playAgainBtn.visibility = View.VISIBLE
            }
            "DRAW" -> {
                statusText.text = "It's a Draw!"
                statusText.setTextColor(Color.LTGRAY)
                playAgainBtn.visibility = View.VISIBLE
            }
            "CONTINUE" -> {
                game.switchPlayer()
                updateStatusUI(statusText)
            }
        }
    }

    private fun updateStatusUI(statusText: TextView) {
        statusText.text = "Player ${game.activePlayer}'s Turn"
        val color = if (game.activePlayer == "X") "#F806CC" else "#A91079"
        statusText.setTextColor(Color.parseColor(color))
    }

    private fun resetUI(buttons: Array<Button>, statusText: TextView, playAgainBtn: Button) {
        for (btn in buttons) {
            btn.text = ""
        }
        updateStatusUI(statusText)
        playAgainBtn.visibility = View.INVISIBLE
    }
}
