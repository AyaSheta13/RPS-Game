package com.example.rpsgame.ui.screen.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.rpsgame.data.model.GameChoice
import com.example.rpsgame.data.model.PlayerType
import com.example.rpsgame.ui.components.*
import com.example.rpsgame.ui.navigation.NavRoutes
import com.example.rpsgame.R
import com.example.rpsgame.ui.theme.PrimaryLightColor

@Composable
fun GameScreen(
    navController: NavController,
    totalRounds: Int,
    playerType: String,
    viewModel: GameViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        val type = when (playerType.lowercase()) {
            "friend" -> PlayerType.FRIEND
            else -> PlayerType.COMPUTER
        }
        viewModel.startGame(totalRounds, type)
    }

    if (uiState.isGameFinished) {
        LaunchedEffect(Unit) {
            navController.navigate(NavRoutes.RESULT) {
                popUpTo(NavRoutes.GAME) { inclusive = true }
            }
        }
    }

    Scaffold { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                // Score display
                ScoreCard(
                    playerScore = uiState.playerScore,
                    computerScore = uiState.computerScore,
                    playerType = uiState.playerType,
                    modifier = Modifier.fillMaxWidth()
                )

                // Round progress
                GameProgressBar(
                    currentRound = minOf(uiState.currentRound - 1, uiState.totalRounds),
                    totalRounds = uiState.totalRounds,
                    modifier = Modifier.fillMaxWidth()
                )

                // Player turn indicator (Friend mode)
                if (uiState.playerType == PlayerType.FRIEND &&
                    uiState.showChoiceButtons &&
                    !uiState.isCountdownActive &&
                    !uiState.isRevealingChoices
                ) {

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        ),
                        border = BorderStroke(
                            width = 2.dp,
                            color = PrimaryLightColor
                        )
                    ) {
                        Text(
                            text = if (uiState.isFriendModeSecondPlayer)
                                "Player 2: Choose Your Move"
                            else
                                "Player 1: Choose Your Move",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp, horizontal = 8.dp)
                        )
                    }
                }

                // Choices display
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ChoiceCard(
                        choice = uiState.playerChoice,
                        title = if (uiState.playerType == PlayerType.FRIEND) "PLAYER 1" else "YOU",
                        isRevealed = uiState.isRevealingChoices || uiState.isGameFinished,
                        choiceRevealed = uiState.playerChoiceRevealed,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    ChoiceCard(
                        choice = uiState.computerChoice,
                        title = if (uiState.playerType == PlayerType.FRIEND)
                            "PLAYER 2"
                        else
                            uiState.playerType.name.uppercase(),
                        isRevealed = uiState.isRevealingChoices || uiState.isGameFinished,
                        choiceRevealed = uiState.computerChoiceRevealed,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(140.dp))

                // Choice Buttons
                if (uiState.showChoiceButtons &&
                    !uiState.isCountdownActive &&
                    !uiState.isRevealingChoices &&
                    !uiState.isGameFinished
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "CHOOSE YOUR MOVE",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            ChoiceButton(
                                text = "ROCK",
                                imageResId = R.drawable.rock,
                                onClick = { viewModel.selectChoice(GameChoice.ROCK) },
                                modifier = Modifier.weight(1f)
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            ChoiceButton(
                                text = "PAPER",
                                imageResId = R.drawable.paper,
                                onClick = { viewModel.selectChoice(GameChoice.PAPER) },
                                modifier = Modifier.weight(1f)
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            ChoiceButton(
                                text = "SCISSORS",
                                imageResId = R.drawable.scissors,
                                onClick = { viewModel.selectChoice(GameChoice.SCISSORS) },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }

                // Game info
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "${uiState.totalRounds} Rounds   .",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = "  vs ${if (uiState.playerType == PlayerType.FRIEND) "Friend" else "Computer"}",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }

            if (uiState.isCountdownActive) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 120.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    CountdownText(
                        count = uiState.countdownValue,
                        onCountdownFinished = { /* handled in ViewModel */ }
                    )
                }
            }

            if (uiState.isRevealingChoices && uiState.resultText != null) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 140.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    AnimatedResultText(
                        resultText = uiState.resultText!!
                    )
                }
            }

        }
    }
}
