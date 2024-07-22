package com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.data.network

import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.data.network.model.GameData
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class FirebaseService @Inject constructor(private val reference: DatabaseReference) {

    companion object {
        private const val GAME_PATH = "games"
    }

    fun createGame(gameData: GameData): String {
        val gameReference = reference.child(GAME_PATH).push()
        val key = gameReference.key
        val newGame = gameData.copy(gameId = key)
        gameReference.setValue(newGame)
        return newGame.gameId.orEmpty()
    }
}