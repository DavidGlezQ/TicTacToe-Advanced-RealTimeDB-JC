package com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.model.GameModel
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.model.PlayerType

@Composable
fun GameScreen(
    gameViewModel: GameViewModel = hiltViewModel(),
    gameId: String,
    userId: String,
    owner: Boolean
) {
    LaunchedEffect(true) {
        gameViewModel.joinGame(gameId, userId, owner)
    }
    val game: GameModel? by gameViewModel.game.collectAsState()
    val winner: PlayerType? by gameViewModel.winner.collectAsState()

    if (winner != null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            val currentWinner = if (winner == PlayerType.FirstPlayer) "Player 1" else "Player 2"
            Text(text = "Winner is $currentWinner")
        }
    } else {
        Board(game = game, onItemSelected = { gameViewModel.onItemSelected(it) })
    }
}

//@Preview(showBackground = true)
@Composable
fun Board(game: GameModel?, onItemSelected: (Int) -> Unit) {
    if (game == null) return
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = game.gameId)
        val status = if (game.isGameReady) {
            if (game.isMyTurn) {
                "Your turn"
            } else {
                "Opponent's turn"
            }
        } else {
            "Game is not ready"
        }

        Text(text = status)

        Row {
            GameItem(game.board[0]) { onItemSelected(0) }
            GameItem(game.board[1]) { onItemSelected(1) }
            GameItem(game.board[2]) { onItemSelected(2) }
        }

        Row {
            GameItem(game.board[3]) { onItemSelected(3) }
            GameItem(game.board[4]) { onItemSelected(4) }
            GameItem(game.board[5]) { onItemSelected(5) }
        }

        Row {
            GameItem(game.board[6]) { onItemSelected(6) }
            GameItem(game.board[7]) { onItemSelected(7) }
            GameItem(game.board[8]) { onItemSelected(8) }
        }
    }
}

@Composable
fun GameItem(playerType: PlayerType, onItemSelected: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(12.dp)
            .size(64.dp)
            .border(BorderStroke(2.dp, Color.Black))
            .clickable { onItemSelected() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = playerType.symbol)
    }
}