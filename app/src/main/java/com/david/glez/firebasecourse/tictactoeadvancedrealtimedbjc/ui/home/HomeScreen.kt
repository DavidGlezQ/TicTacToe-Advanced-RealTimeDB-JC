@file:OptIn(ExperimentalMaterial3Api::class)

package com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.R
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.theme.Accent
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.theme.Orange1
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.theme.Orange2
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.theme.background

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToGame: (String, String, Boolean) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(background)
    ) {
        Header()

        Body(
            onCreateGame = { homeViewModel.onCreateGame(navigateToGame) },
            onJoinGame = { homeViewModel.onJoinGame(it, navigateToGame) })
    }
}

@Composable
fun Body(onCreateGame: () -> Unit, onJoinGame: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(24.dp),
        colors = CardDefaults.cardColors(contentColor = background),
        border = BorderStroke(2.dp, Orange1),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            var createName by remember {
                mutableStateOf(true)
            }
            Switch(
                checked = createName,
                onCheckedChange = { createName = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Orange2,
                    checkedTrackColor = Orange1,
                    uncheckedThumbColor = Orange1,
                    uncheckedTrackColor = Orange2,
                    uncheckedBorderColor = Orange1
                )
            )
            HorizontalDivider(
                modifier = Modifier
                    .height(24.dp)
                    .padding(end = 24.dp, start = 24.dp)
            )
            AnimatedContent(targetState = createName, label = "") {
                when (it) {
                    true -> CreateGame(onCreateGame)
                    false -> JoinGame(onJoinGame)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun Header() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .size(200.dp)
                .padding(12.dp)
                .clip(RoundedCornerShape(24.dp))
                .border(2.dp, Orange1, RoundedCornerShape(24.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "logo"
            )
        }
        Text(text = "Firebase", fontSize = 28.sp, color = Orange1, fontWeight = FontWeight.Bold)
        Text(text = "Tic Tac Toe", fontSize = 28.sp, color = Orange2, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun CreateGame(onCreateGame: () -> Unit) {
    Button(onClick = { onCreateGame() }, colors = ButtonDefaults.buttonColors(Orange1)) {
        Text(text = "Create Game")
    }
}

@Composable
fun JoinGame(onJoinGame: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = Orange1,
                focusedBorderColor = Orange1,
                unfocusedBorderColor = Accent
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onJoinGame(text) },
            enabled = text.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(Orange1)
        ) {
            Text(text = "Join Game")
        }
    }
}