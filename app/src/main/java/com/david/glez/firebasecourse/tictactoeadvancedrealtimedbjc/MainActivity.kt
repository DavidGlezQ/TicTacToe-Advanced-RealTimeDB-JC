package com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.theme.TicTacToeAdvancedRealTimeDBJCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeAdvancedRealTimeDBJCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TicTacToeAdvancedRealTimeDBJCTheme {
        Greeting("Android")
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
 *
* */