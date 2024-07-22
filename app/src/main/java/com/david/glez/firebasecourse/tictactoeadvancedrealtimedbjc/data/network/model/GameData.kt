package com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.data.network.model

import android.icu.util.Calendar

data class GameData(
    val board: List<Int?>? = null,
    val gameId: String? = null,
    val player1: Player? = null,
    val player2: Player? = null,
    val playerTurn: Player? = null
)

data class Player(
    val userId: String? = Calendar.getInstance().timeInMillis.hashCode().toString(),
    val playerType: Int? = null
)
