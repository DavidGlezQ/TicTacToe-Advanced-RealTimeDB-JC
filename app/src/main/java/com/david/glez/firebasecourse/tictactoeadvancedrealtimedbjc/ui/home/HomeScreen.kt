package com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToGame: (String, String, Boolean) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(2f))

        CreateGame {
            homeViewModel.onCreateGame(navigateToGame)
        }

        Spacer(modifier = Modifier.weight(1f))

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
        )

        Spacer(modifier = Modifier.weight(2f))


        JoinGame {
            homeViewModel.onJoinGame(it, navigateToGame)
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun CreateGame(onCreateGame: () -> Unit) {
    Button(onClick = { onCreateGame() }) {
        Text(text = "Create Game")
    }
}

@Composable
fun JoinGame(onJoinGame: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(value = text, onValueChange = { text = it })

    Button(onClick = { onJoinGame(text) }, enabled = text.isNotEmpty()) {
        Text(text = "Join Game")
    }
}