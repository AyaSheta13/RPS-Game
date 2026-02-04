package com.example.rpsgame.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import com.example.rpsgame.ui.theme.SecondaryDarkColor

@Composable
fun ChoiceCard(
    choice: GameChoice?,
    title: String,
    isRevealed: Boolean = false,
    choiceRevealed: Boolean = true,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(190.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent // الكارد شفاف
        ),
        border = BorderStroke(
            width = 2.dp,
            color = SecondaryDarkColor // لون الحواف
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp) // بدون ظل
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = title,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                color = SecondaryDarkColor
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(90.dp)
            ) {
                if (choice != null && isRevealed && choiceRevealed) {
                    val iconRes = when (choice) {
                        GameChoice.ROCK -> R.drawable.rock
                        GameChoice.PAPER -> R.drawable.paper
                        GameChoice.SCISSORS -> R.drawable.scissors
                    }

                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = choice.name,
                        modifier = Modifier.size(70.dp),
                        tint = SecondaryDarkColor
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_question),
                        contentDescription = "Not chosen yet",
                        modifier = Modifier.size(70.dp),
                        tint = SecondaryDarkColor.copy(alpha = 0.4f)
                    )
                }
            }

            Text(
                text = when {
                    choice != null && isRevealed && choiceRevealed -> choice.name
                    else -> ""
                },
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = SecondaryDarkColor
            )
        }
    }
}
