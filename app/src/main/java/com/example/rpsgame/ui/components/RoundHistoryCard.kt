package com.example.rpsgame.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rpsgame.R
import com.example.rpsgame.data.model.GameChoice
import com.example.rpsgame.data.model.RoundResult
import com.example.rpsgame.ui.theme.ErrorColor
import com.example.rpsgame.ui.theme.PrimaryDarkColor
import com.example.rpsgame.ui.theme.SuccessColor

@Composable
fun RoundHistoryCard(
    roundNumber: Int,
    playerChoice: GameChoice,
    computerChoice: GameChoice,
    result: RoundResult,
    modifier: Modifier = Modifier
) {
    val mainColor = when (result) {
        RoundResult.PLAYER_WIN -> SuccessColor
        RoundResult.COMPUTER_WIN -> ErrorColor
        RoundResult.DRAW -> PrimaryDarkColor
    }

    Card(
        modifier = modifier
            .width(100.dp)
            .height(120.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(2.dp, mainColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = "R$roundNumber",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = mainColor
            )

            Spacer(modifier = Modifier.height(8.dp))

            val playerIcon = when (playerChoice) {
                GameChoice.ROCK -> R.drawable.rock
                GameChoice.PAPER -> R.drawable.paper
                GameChoice.SCISSORS -> R.drawable.scissors
            }

            Icon(
                painter = painterResource(id = playerIcon),
                contentDescription = playerChoice.name,
                modifier = Modifier.size(32.dp),
                tint = mainColor
            )

            Spacer(modifier = Modifier.height(4.dp))

            val resultIcon = when (result) {
                RoundResult.PLAYER_WIN -> R.drawable.ic_check
                RoundResult.COMPUTER_WIN -> R.drawable.ic_close
                RoundResult.DRAW -> R.drawable.ic_equal
            }

            Icon(
                painter = painterResource(id = resultIcon),
                contentDescription = result.toString(),
                modifier = Modifier.size(24.dp),
                tint = mainColor
            )

            Spacer(modifier = Modifier.height(4.dp))

            val computerIcon = when (computerChoice) {
                GameChoice.ROCK -> R.drawable.rock
                GameChoice.PAPER -> R.drawable.paper
                GameChoice.SCISSORS -> R.drawable.scissors
            }

            Icon(
                painter = painterResource(id = computerIcon),
                contentDescription = computerChoice.name,
                modifier = Modifier.size(32.dp),
                tint = mainColor
            )
        }
    }
}