package com.example.rpsgame.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rpsgame.R
import com.example.rpsgame.data.model.PlayerType
import com.example.rpsgame.ui.theme.PrimaryDarkColor
import com.example.rpsgame.ui.theme.SurfaceColor

@Composable
fun WinnerCard(
    playerType: PlayerType,
    playerScore: Int,
    computerScore: Int,
    modifier: Modifier = Modifier
) {
    val result = when {
        playerType == PlayerType.COMPUTER && playerScore > computerScore ->
            Pair(R.drawable.trophy, "You Win!")
        playerType == PlayerType.COMPUTER && computerScore > playerScore ->
            Pair(R.drawable.robot, "Computer Wins!")
        playerType == PlayerType.FRIEND && playerScore > computerScore ->
            Pair(R.drawable.trophy, "Player 1 Wins!")
        playerType == PlayerType.FRIEND && computerScore > playerScore ->
            Pair(R.drawable.trophy, "Player 2 Wins!")
        else ->
            Pair(R.drawable.ic_equal, "It's a Draw!")
    }

    val iconResId = result.first
    val title = result.second

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = painterResource(id = iconResId),
                    contentDescription = "Result Icon",
                    modifier = Modifier.size(110.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = PrimaryDarkColor
                    )
                )
            }
        }
    }
}