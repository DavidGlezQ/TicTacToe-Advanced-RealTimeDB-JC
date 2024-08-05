package com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.R
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.theme.Orange1
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.theme.background

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToGame: (String, String, Boolean) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(background)
    ) {
        Header()

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
fun Header() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .clip(RoundedCornerShape(24.dp))
            .border(2.dp, Orange1, RoundedCornerShape(24.dp)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "logo"
        )
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