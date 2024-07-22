package com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.core.ContentWrapper
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.home.HomeScreen
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.theme.TicTacToeAdvancedRealTimeDBJCTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navigationController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeAdvancedRealTimeDBJCTheme {
                navigationController = rememberNavController()
                ContentWrapper(navigationController = navigationController)
            }
        }
    }
}


/**
 * Listado de partidas
 * Board: List<String> = 9 posiciones
 * ID de partida
 * Player 1 y 2:
 * playerID
 * playerType
 * PlayerTurn
 * */