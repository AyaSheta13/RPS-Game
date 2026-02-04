package com.example.rpsgame.ui.screen.game

import com.example.rpsgame.data.model.GameChoice
import com.example.rpsgame.data.model.PlayerType
import com.example.rpsgame.data.model.RoundHistory
import com.example.rpsgame.data.model.RoundResult

data class GameUiState(
    val playerScore: Int = 0,
    val computerScore: Int = 0,
    val currentRound: Int = 1,
    val totalRounds: Int = 3,
    val playerChoice: GameChoice? = null,
    val computerChoice: GameChoice? = null,
    val roundHistory: List<RoundHistory> = emptyList(),
    val resultText: String? = null,
    val winner: String? = null,
    val isGameActive: Boolean = false,
    val isGameFinished: Boolean = false,
    val isCountdownActive: Boolean = false,
    val isRevealingChoices: Boolean = false,
    val isFriendModeSecondPlayer: Boolean = false,
    val playerChoiceRevealed: Boolean = false,
    val computerChoiceRevealed: Boolean = false,
    val showChoiceButtons: Boolean = true, // حالة جديدة للتحكم في إظهار أزرار الاختيار
    val countdownValue: Int = 3,
    val playerType: PlayerType = PlayerType.COMPUTER,
    val isChoiceSelected: Boolean = false
)