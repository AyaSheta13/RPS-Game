package com.example.rpsgame.ui.screen.result

import com.example.rpsgame.data.model.GameState

data class ResultUiState(
    val gameState: GameState = GameState(),
    val isLoading: Boolean = false
)