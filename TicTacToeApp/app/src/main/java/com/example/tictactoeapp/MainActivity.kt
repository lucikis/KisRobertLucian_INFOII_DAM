package com.example.tictactoeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoeapp.ui.theme.TicTacToeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TicTacToeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    @Composable
    fun TicTacToeScreen(modifier: Modifier = Modifier) {
        var board by remember { mutableStateOf(List(3) { MutableList(3) { "" } }) }
        var currentPlayer by remember { mutableStateOf("X") }
        var winner by remember { mutableStateOf<String?>(null) }

        fun checkWinner(): String? {
            val lines = listOf(
                listOf(board[0][0], board[0][1], board[0][2]),
                listOf(board[1][0], board[1][1], board[1][2]),
                listOf(board[2][0], board[2][1], board[2][2]),

                listOf(board[0][0], board[1][0], board[2][0]),
                listOf(board[0][1], board[1][1], board[2][1]),
                listOf(board[0][2], board[1][2], board[2][2]),

                listOf(board[0][0], board[1][1], board[2][2]),
                listOf(board[0][2], board[1][1], board[2][0])
            )

            for (line in lines) {
                if (line[0].isNotEmpty() && line[0] == line[1] && line[1] == line[2]) {
                    return line[0]
                }
            }

            return if (board.all { row -> row.all { it.isNotEmpty() } }) "Draw" else null
        }

        fun restartGame() {
            board = List(3) { MutableList(3) { "" } }
            currentPlayer = "X"
            winner = null
        }

        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Tic-Tac-Toe", fontSize = 24.sp)

            Spacer(modifier = Modifier.height(20.dp))

            for (row in 0..2) {
                Row {
                    for (col in 0..2) {
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .border(2.dp, Color.Black)
                                .background(Color.LightGray)
                                .clickable {
                                    if (board[row][col].isEmpty() && winner == null) {
                                        board = board.mapIndexed { r, rowList ->
                                            rowList.mapIndexed { c, cell ->
                                                if (r == row && c == col) currentPlayer else cell
                                            }.toMutableList()
                                        }
                                        winner = checkWinner()
                                        if (winner == null) {
                                            currentPlayer = if (currentPlayer == "X") "O" else "X"
                                        }
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = board[row][col], fontSize = 32.sp)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (winner != null) {
                Text(text = if (winner == "Draw") "It's a Draw!" else "$winner Wins!", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = { restartGame() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff1b1d7a),
                        contentColor = Color.White
                    )
                ) {
                    Text("Restart Game")
                }
            } else {
                Text(text = "Current Player: $currentPlayer", fontSize = 20.sp)
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun TicTacToePreview() {
        TicTacToeAppTheme {
            TicTacToeScreen()
        }
    }
}