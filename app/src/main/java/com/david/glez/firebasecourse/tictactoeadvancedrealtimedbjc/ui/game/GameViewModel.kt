package com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.data.network.FirebaseService
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.model.GameModel
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.model.PlayerModel
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.model.PlayerType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(private val firebaseService: FirebaseService) :
    ViewModel() {
    private lateinit var userId: String
    private var _game = MutableStateFlow<GameModel?>(null)
    val game: StateFlow<GameModel?> = _game
    fun joinGame(gameId: String, userId: String, owner: Boolean) {
        this.userId = userId
        if (owner) {
            joinGameAsOwner(gameId)
        } else {
            joinGameAsGuest(gameId)
        }
    }

    private fun joinGameAsGuest(gameId: String) {
        viewModelScope.launch {
            firebaseService.joinGame(gameId).take(1).collect {
                var result = it
                if (result != null) {
                    result = result.copy(
                        player2 = PlayerModel(
                            userId = userId,
                            playerType = PlayerType.SecondPlayer
                        )
                    )
                    firebaseService.updateGame(result.toData())
                }
            }

            firebaseService.joinGame(gameId).collect {
                val result = it?.copy(isGameReady = it.player2 != null)
                _game.value = result
            }
        }
    }

    private fun joinGameAsOwner(gameId: String) {
        viewModelScope.launch {
            firebaseService.joinGame(gameId).collect {
                val result = it?.copy(isGameReady = it.player2 != null)
                _game.value = result
            }
        }
    }

    fun onItemSelected(position: Int) {
        val currentGame = _game.value ?: return
        if (currentGame.isGameReady && currentGame.board[position] == PlayerType.Empty) {
            viewModelScope.launch {
                val newBoard = currentGame.board.toMutableList()
                newBoard[position] = getPlayer() ?: PlayerType.Empty
                firebaseService.updateGame(
                    currentGame.copy(
                        board = newBoard, playerTurn = getEnemyPlayer()!!
                    ).toData()
                )
            }
        }
    }

    private fun getPlayer(): PlayerType? {
        return when {
            (game.value?.player1?.userId == userId) -> PlayerType.FirstPlayer
            (game.value?.player2?.userId == userId) -> PlayerType.SecondPlayer
            else -> null
        }
    }

    private fun getEnemyPlayer(): PlayerModel? {
        return if (game.value?.player1?.userId == userId) game.value?.player2 else game.value?.player1
    }
}