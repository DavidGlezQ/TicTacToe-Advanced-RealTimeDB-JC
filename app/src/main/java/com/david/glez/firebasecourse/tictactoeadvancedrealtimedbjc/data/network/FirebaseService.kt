package com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.data.network

import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.data.network.model.GameData
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class FirebaseService @Inject constructor(private val reference: DatabaseReference) {

    companion object {
        private const val GAME_PATH = "games"
    }

    fun createGame(gameData: GameData) {
        val gameReference = reference.child(GAME_PATH).push()
        gameReference.setValue(gameData)
    }
}