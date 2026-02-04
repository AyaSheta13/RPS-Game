package com.example.rpsgame.ui.screen.main

import com.example.rpsgame.data.model.GameMode
import com.example.rpsgame.data.model.PlayerType

data class MainUiState(
    val selectedMode: GameMode = GameMode.THREE_ROUNDS,
    val playerType: PlayerType = PlayerType.COMPUTER,
    val isLoading: Boolean = false
)