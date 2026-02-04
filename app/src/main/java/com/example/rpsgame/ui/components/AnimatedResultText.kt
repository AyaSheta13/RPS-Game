package com.example.rpsgame.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.rpsgame.ui.theme.SecondaryDarkColor
import kotlinx.coroutines.delay

@Composable
fun AnimatedResultText(
    resultText: String,
    modifier: Modifier = Modifier
) {
    val scale = remember { Animatable(0.3f) }
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(resultText) {
        scale.animateTo(
            targetValue = 1.4f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )

        alpha.animateTo(1f, animationSpec = tween(300))

        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(200)
        )

        delay(1500)

        alpha.animateTo(0f, animationSpec = tween(400))
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = resultText,
            fontSize = 48.sp,
            fontWeight = FontWeight.Black,
            color = SecondaryDarkColor,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .scale(scale.value)
                .alpha(alpha.value)
        )
    }
}
