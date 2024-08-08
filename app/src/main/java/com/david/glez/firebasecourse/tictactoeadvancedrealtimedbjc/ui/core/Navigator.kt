package com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.game.GameScreen
import com.david.glez.firebasecourse.tictactoeadvancedrealtimedbjc.ui.home.HomeScreen

@Composable
fun ContentWrapper(navigationController: NavHostController) {
    NavHost(navController = navigationController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            HomeScreen(navigateToGame = { gameId, userId, owner ->
                navigationController.navigate(
                    Routes.Game.createRoute(gameId = gameId, userId = userId, owner = owner)
                )
            })
        }
        composable(Routes.Game.route, arguments = listOf(
            navArgument("gameId") { type = NavType.StringType },
            navArgument("userId") { type = NavType.StringType },
            navArgument("owner") { type = NavType.BoolType }
        )) {
            GameScreen(
                gameId = it.arguments?.getString("gameId").orEmpty(),
                userId = it.arguments?.getString("userId").orEmpty(),
                owner = it.arguments?.getBoolean("owner") ?: false,
                navigateToHome = { navigationController.popBackStack() }
            )
        }
    }
}

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Game : Routes("game/{gameId}/{userId}/{owner}") {
        fun createRoute(gameId: String, userId: String, owner: Boolean) =
            "game/$gameId/$userId/$owner"
    }
}