package com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.home

import android.icu.util.Calendar
import androidx.lifecycle.ViewModel
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.data.network.FirebaseService
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.data.network.model.GameData
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.data.network.model.PlayerData
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.utils.createUserId
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val firebaseService: FirebaseService) :
    ViewModel() {

    fun onCreateGame(navigateToGame: (String, String, Boolean) -> Unit) {
        val game = createNewGame()
        val gameId = firebaseService.createGame(gameData = game)
        val userId = game.playerData1?.userId
        val owner = true
        navigateToGame(gameId, userId.orEmpty(), owner)
    }

    fun onJoinGame(gameId: String, navigateToGame: (String, String, Boolean) -> Unit) {
        val owner = false
        navigateToGame(gameId, createUserId(), owner)
    }

    private fun createNewGame(): GameData {
        val currentPlayer = PlayerData(playerType = 1)
        return GameData(
            board = List(9) { 0 },
            playerData1 = currentPlayer,
            playerData2 = null,
            playerDataTurn = currentPlayer
        )
    }
}