package com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.data.network.FirebaseService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(private val firebaseService: FirebaseService): ViewModel() {
    lateinit var userId: String

    fun joinGame(gameId: String, userId: String, owner: Boolean) {
        this.userId = userId
        if (owner) {
            joinGameAsOwner(gameId)
        } else {
            joinGameAsGuest(gameId)
        }
    }

    private fun joinGameAsGuest(gameId: String) {

    }

    private fun joinGameAsOwner(gameId: String) {
        viewModelScope.launch {
            firebaseService.joinGame(gameId).collect {
                Log.i("Get data", it.toString())
            }
        }
    }

}