package com.example.rpsgame.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rpsgame.ui.theme.PrimaryDarkColor
import com.example.rpsgame.ui.theme.PrimaryLightColor
import com.example.rpsgame.ui.theme.SecondaryDarkColor

@Composable
fun GameProgressBar(
    currentRound: Int,
    totalRounds: Int,
    modifier: Modifier = Modifier
) {
    val safeCurrentRound = if (totalRounds > 0) minOf(currentRound, totalRounds) else 0
    val targetProgress =
        if (totalRounds > 0) safeCurrentRound.toFloat() / totalRounds.toFloat() else 0f

    // أنيميشن للحركة
    val animatedProgress by animateFloatAsState(
        targetValue = targetProgress,
        animationSpec = tween(durationMillis = 600),
        label = "progressAnimation"
    )

    Column(modifier = modifier.fillMaxWidth()) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Round $safeCurrentRound / $totalRounds",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = SecondaryDarkColor
            )

            Text(
                text = "${(animatedProgress * 100).toInt()}%",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = SecondaryDarkColor
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(14.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(PrimaryLightColor)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(animatedProgress)
                    .clip(RoundedCornerShape(10.dp))
                    .background(SecondaryDarkColor)
            )
        }
    }
}