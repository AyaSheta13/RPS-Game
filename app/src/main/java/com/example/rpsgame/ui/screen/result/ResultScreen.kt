package com.example.rpsgame.ui.screen.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.rpsgame.ui.components.RoundHistoryCard
import com.example.rpsgame.ui.components.ScoreCard
import com.example.rpsgame.ui.components.WinnerCard
import com.example.rpsgame.ui.theme.*

@Composable
fun ResultScreen(
    navController: NavController,
    viewModel: ResultViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadGameResult()
    }

    val roundHistory = uiState.gameState.roundHistory
    val playerType = uiState.gameState.playerType
    val playerScore = uiState.gameState.playerScore
    val computerScore = uiState.gameState.computerScore

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Winner Card - تم التعديل هنا
            WinnerCard(
                playerType = playerType,
                playerScore = playerScore,
                computerScore = computerScore,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(320.dp)
                    .padding(top = 32.dp, bottom = 24.dp)
            )

            // Score Card مع تمرير نوع اللاعب
            ScoreCard(
                playerScore = playerScore,
                computerScore = computerScore,
                playerType = playerType,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            )

            // Round History Section
            if (roundHistory.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Round History",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                        )
                    )

                    Text(
                        text = "${roundHistory.size} rounds",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = TextSecondary
                        )
                    )
                }

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(roundHistory) { history ->
                        RoundHistoryCard(
                            roundNumber = history.roundNumber,
                            playerChoice = history.playerChoice,
                            computerChoice = history.computerChoice,
                            result = history.result,
                            modifier = Modifier.width(100.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Play Again Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                SecondaryDarkColor,
                                PrimaryColor
                            )
                        )
                    )
            ) {
                Button(
                    onClick = { viewModel.playAgain(navController) },
                    modifier = Modifier.fillMaxSize(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent // 👈 مهم لجعل التدرج ظاهر
                    ),
                    enabled = !uiState.isLoading
                ) {
                    if (uiState.isLoading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            strokeWidth = 2.dp,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Text(
                            text = "Play Again",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                            ),
                            color = Color.White
                        )
                    }
                }
            }


            TextButton(
                onClick = { viewModel.navigateToMain(navController) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                enabled = !uiState.isLoading
            ) {
                Text(
                    text = "Main Menu",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = TextSecondary
                    )
                )
            }
        }
    }
}