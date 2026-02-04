package com.example.rpsgame.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.rpsgame.ui.screen.game.GameScreen
import com.example.rpsgame.ui.screen.main.MainScreen
import com.example.rpsgame.ui.screen.result.ResultScreen

fun NavGraphBuilder.appNavGraph(navController: NavHostController) {

    // الشاشة الرئيسية
    composable(NavRoutes.MAIN) {
        MainScreen(navController = navController)
    }

    // شاشة اللعبة مع تمرير arguments
    composable(
        route = NavRoutes.GAME,
        arguments = NavRoutes.gameArguments
    ) { backStackEntry ->
        val totalRounds = backStackEntry.arguments?.getInt("totalRounds") ?: 3
        val playerType = backStackEntry.arguments?.getString("playerType") ?: "computer"

        GameScreen(
            navController = navController,
            totalRounds = totalRounds,
            playerType = playerType
        )
    }

    // شاشة النتيجة
    composable(NavRoutes.RESULT) {
        ResultScreen(navController = navController)
    }
}