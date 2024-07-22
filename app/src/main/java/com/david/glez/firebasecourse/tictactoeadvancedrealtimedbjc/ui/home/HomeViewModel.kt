package com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.home

import androidx.lifecycle.ViewModel
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.data.network.FirebaseService
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.data.network.model.GameData
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.data.network.model.PlayerData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val firebaseService: FirebaseService) :
    ViewModel() {

    fun onCreateGame() {
        firebaseService.createGame(gameData = createNewGame())
    }

    fun onJoinGame(gameId: String) {
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