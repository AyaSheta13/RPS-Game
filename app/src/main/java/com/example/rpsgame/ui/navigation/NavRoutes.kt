package com.example.rpsgame.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

object NavRoutes {
    const val MAIN = "main"
    const val GAME = "game/{totalRounds}/{playerType}"
    const val RESULT = "result"

    // تعريف arguments لشاشة اللعبة
    val gameArguments = listOf(
        navArgument("totalRounds") {
            type = NavType.IntType
            defaultValue = 3
        },
        navArgument("playerType") {
            type = NavType.StringType
            defaultValue = "computer"
        }
    )

    // دالة لبناء Route ديناميكي
    fun buildGameRoute(totalRounds: Int, playerType: String): String {
        return "game/$totalRounds/$playerType"
    }
}