package com.example.rpsgame.ui.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.rpsgame.R
import com.example.rpsgame.data.model.GameMode
import com.example.rpsgame.data.model.PlayerType
import com.example.rpsgame.ui.components.GameModeChip
import com.example.rpsgame.ui.components.PlayerTypeButton
import com.example.rpsgame.ui.navigation.NavRoutes
import com.example.rpsgame.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.init()
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            Text(
                text = "Rock Paper Scissors",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = PrimaryColor
                ),
                modifier = Modifier.padding(top = 48.dp, bottom = 16.dp)
            )

            Text(
                text = "Choose your destiny",
                style = MaterialTheme.typography.bodyLarge.copy(color = TextSecondary),
                modifier = Modifier.padding(bottom = 48.dp)
            )

            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .background(PrimaryColor),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.my_circle_image),
                    contentDescription = "Circle Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                )
            }


            Spacer(modifier = Modifier.height(48.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Match Length",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_clock),
                            contentDescription = "Clock",
                            tint = PrimaryColor,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        GameMode.entries.forEach { mode ->
                            GameModeChip(
                                text = "${mode.rounds} Rounds",
                                selected = uiState.selectedMode == mode,
                                onClick = { viewModel.selectGameMode(mode) },
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PlayerTypeButton(
                    text = "VS Computer",
                    iconResId = R.drawable.ic_computer,
                    selected = uiState.playerType == PlayerType.COMPUTER,
                    onClick = { viewModel.selectPlayerType(PlayerType.COMPUTER) },
                    modifier = Modifier.weight(0.48f)
                )

                Spacer(modifier = Modifier.width(16.dp))

                PlayerTypeButton(
                    text = "VS Friend",
                    iconResId = R.drawable.ic_friend,
                    selected = uiState.playerType == PlayerType.FRIEND,
                    onClick = { viewModel.selectPlayerType(PlayerType.FRIEND) },
                    modifier = Modifier.weight(0.48f)
                )
            }


            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    viewModel.startGame { totalRounds, playerType ->
                        val route = NavRoutes.buildGameRoute(totalRounds, playerType)
                        navController.navigate(route)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(16.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp),
                contentPadding = PaddingValues()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(PrimaryColor, SecondaryDarkColor)
                            ),
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (uiState.isLoading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            strokeWidth = 3.dp,
                            modifier = Modifier.size(28.dp)
                        )
                    } else {
                        Text(
                            text = "Start Game",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                    }
                }
            }


        }
    }
}