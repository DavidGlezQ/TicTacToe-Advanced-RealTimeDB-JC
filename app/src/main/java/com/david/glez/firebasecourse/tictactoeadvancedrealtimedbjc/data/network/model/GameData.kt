package com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.data.network.model

import android.icu.util.Calendar
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.model.GameModel
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.model.PlayerModel
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.model.PlayerType

data class GameData(
    val board: List<Int?>? = null,
    val gameId: String? = null,
    val playerData1: PlayerData? = null,
    val playerData2: PlayerData? = null,
    val playerDataTurn: PlayerData? = null
) {
    fun toModel(): GameModel {
        return GameModel(
            board = board?.map { PlayerType.getPlayerById(it) }.orEmpty(),
            gameId = gameId.orEmpty(),
            player1 = playerData1!!.toModel(),
            player2 = playerData2?.toModel(),
            playerTurn = playerDataTurn!!.toModel()
        )
    }
}

data class PlayerData(
    val userId: String? = Calendar.getInstance().timeInMillis.hashCode().toString(),
    val playerType: Int? = null
) {
    fun toModel(): PlayerModel {
        return PlayerModel(userId = userId!!, playerType = PlayerType.getPlayerById(playerType))
    }
}
