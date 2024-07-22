package com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.game.GameScreen
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.home.HomeScreen

@Composable
fun ContentWrapper(navigationController: NavHostController) {
    NavHost(navController = navigationController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            HomeScreen(navigateToGame = { navigationController.navigate(Routes.Game.route) })
        }
        composable(Routes.Game.route) {
            GameScreen()
        }
    }
}

sealed class Routes (val route: String) {
    object Home: Routes("home")
    object Game: Routes("game")
}