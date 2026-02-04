package com.example.rpsgame.ui.utils

import androidx.compose.ui.Modifier

fun Modifier.conditional(
    condition: Boolean,
    ifTrue: Modifier.() -> Modifier,
    ifFalse: Modifier.() -> Modifier = { this }
): Modifier {
    return if (condition) {
        then(ifTrue(Modifier))
    } else {
        then(ifFalse(Modifier))
    }
}

fun String.capitalizeWords(): String {
    return split(" ").joinToString(" ") { word ->
        word.replaceFirstChar { it.uppercase() }
    }
}

// Extensions for navigation
fun String.toPlayerTypeString(): String {
    return when (this.lowercase()) {
        "computer" -> "Computer"
        "friend" -> "Friend"
        else -> "Computer"
    }
}