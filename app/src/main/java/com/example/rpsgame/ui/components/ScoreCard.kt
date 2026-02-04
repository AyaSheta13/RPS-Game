package com.example.rpsgame.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rpsgame.data.model.PlayerType
import com.example.rpsgame.ui.theme.PrimaryDarkColor
import com.example.rpsgame.ui.theme.PrimaryLightColor

@Composable
fun ScoreCard(
    playerScore: Int,
    computerScore: Int,
    playerType: PlayerType = PlayerType.COMPUTER,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(110.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = PrimaryLightColor // خلفية الكارد
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (playerType == PlayerType.FRIEND) "PLAYER 1" else "YOU",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = PrimaryDarkColor
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = playerScore.toString(),
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryDarkColor
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "VS",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = PrimaryDarkColor.copy(alpha = 0.7f)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = ":",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryDarkColor.copy(alpha = 0.5f)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (playerType == PlayerType.FRIEND) "PLAYER 2" else "COMPUTER",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = PrimaryDarkColor
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = computerScore.toString(),
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryDarkColor
                )
            }
        }
    }
}