package com.example.rpsgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rpsgame.ui.navigation.NavRoutes
import com.example.rpsgame.ui.screen.game.GameScreen
import com.example.rpsgame.ui.screen.main.MainScreen
import com.example.rpsgame.ui.screen.result.ResultScreen
import com.example.rpsgame.ui.theme.RpsGameTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RpsGameTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = NavRoutes.MAIN
                    ) {
                        composable(NavRoutes.MAIN) {
                            MainScreen(navController = navController)
                        }

                        composable(
                            route = NavRoutes.GAME,
                            arguments = listOf(
                                navArgument("totalRounds") { type = NavType.IntType },
                                navArgument("playerType") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val totalRounds = backStackEntry.arguments?.getInt("totalRounds") ?: 3
                            val playerType = backStackEntry.arguments?.getString("playerType") ?: "computer"

                            GameScreen(
                                navController = navController,
                                totalRounds = totalRounds,
                                playerType = playerType
                            )
                        }

                        composable(NavRoutes.RESULT) {
                            ResultScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}