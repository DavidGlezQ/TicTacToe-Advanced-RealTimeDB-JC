package com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.data.network

import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.data.network.model.GameData
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.model.GameModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    fun joinGame(gameId: String): Flow<GameModel?> {
        return reference.database.reference.child("${GAME_PATH}/$gameId").snapshots.map { dataSnapShot ->
            dataSnapShot.getValue(GameData::class.java)?.toModel()
        }
    }

    fun updateGame(gameData: GameData) {
        if (gameData.gameId != null) {
            reference.child(GAME_PATH).child(gameData.gameId).setValue(gameData)
        }
    }
}