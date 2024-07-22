package com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.data.network.model

import android.icu.util.Calendar

data class GameData(
    val board: List<Int?>? = null,
    val gameId: String? = null,
    val playerData1: PlayerData? = null,
    val playerData2: PlayerData? = null,
    val playerDataTurn: PlayerData? = null
)

data class PlayerData(
    val userId: String? = Calendar.getInstance().timeInMillis.hashCode().toString(),
    val playerType: Int? = null
)
