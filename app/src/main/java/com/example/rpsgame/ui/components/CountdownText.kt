package com.example.rpsgame.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.rpsgame.ui.theme.SecondaryDarkColor

@Composable
fun CountdownText(
    count: Int,
    onCountdownFinished: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scale = remember { Animatable(1f) }

    LaunchedEffect(count) {
        if (count > 0) {
            scale.animateTo(
                targetValue = 2f,
                animationSpec = tween(durationMillis = 400)
            )
            scale.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 400)
            )
        } else {
            onCountdownFinished()
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        if (count > 0) {
            Text(
                text = count.toString(),
                fontSize = 72.sp,
                fontWeight = FontWeight.Bold,
                color = SecondaryDarkColor,
                modifier = Modifier.scale(scale.value)
            )
        }
    }
}
