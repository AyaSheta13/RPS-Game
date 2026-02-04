package com.example.rpsgame.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rpsgame.ui.theme.PrimaryColor
import com.example.rpsgame.ui.theme.PrimaryDarkColor
import com.example.rpsgame.ui.theme.PrimaryLightColor
import com.example.rpsgame.ui.theme.SecondaryColor
import com.example.rpsgame.ui.theme.SecondaryDarkColor

@Composable
fun ChoiceButton(
    text: String,
    imageResId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    enabled: Boolean = true
) {
    val backgroundBrush = if (selected) {
        Brush.verticalGradient(listOf(PrimaryDarkColor, PrimaryColor))
    } else {
        Brush.verticalGradient(listOf(Color.Transparent, Color.Transparent))
    }

    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .height(125.dp)
            .background(backgroundBrush, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(2.dp, if (selected) PrimaryDarkColor else SecondaryDarkColor),
        colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = if (selected) 8.dp else 0.dp),
        enabled = enabled
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = text,
                modifier = Modifier.size(60.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = text,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryDarkColor
            )
        }
    }
}

@Composable
fun PlayerTypeButton(
    text: String,
    iconResId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean = false
) {
    val backgroundBrush = if (selected) {
        Brush.verticalGradient(listOf(SecondaryDarkColor, SecondaryColor))
    } else {
        Brush.verticalGradient(listOf(PrimaryLightColor, PrimaryLightColor))
    }

    Card(
        onClick = onClick,
        modifier = modifier.height(130.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = if (selected) 10.dp else 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundBrush, RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = text,
                    modifier = Modifier.size(52.dp),
                    tint = if (selected) Color.White else PrimaryDarkColor
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = text,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (selected) Color.White else PrimaryDarkColor
                )
            }
        }
    }
}