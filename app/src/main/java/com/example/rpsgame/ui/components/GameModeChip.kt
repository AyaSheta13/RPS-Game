package com.example.rpsgame.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rpsgame.ui.theme.BorderColor
import com.example.rpsgame.ui.theme.PrimaryColor
import com.example.rpsgame.ui.theme.PrimaryDarkColor
import com.example.rpsgame.ui.theme.PrimaryLightColor
import com.example.rpsgame.ui.theme.TextSecondary

@Composable
fun GameModeChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundBrush = if (selected) {
        Brush.horizontalGradient(listOf(PrimaryDarkColor, PrimaryColor))
    } else {
        Brush.horizontalGradient(listOf(PrimaryLightColor, PrimaryLightColor))
    }

    Box(
        modifier = modifier
            .height(36.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(backgroundBrush)
            .border(1.dp, if (selected) PrimaryColor else BorderColor, RoundedCornerShape(18.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (selected) Color.White else TextSecondary,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
        )
    }
}