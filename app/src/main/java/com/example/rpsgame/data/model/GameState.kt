package com.example.rpsgame.data.model

data class GameState(
    val playerScore: Int = 0,
    val computerScore: Int = 0,
    val currentRound: Int = 1,
    val totalRounds: Int = 3,
    val playerChoice: GameChoice? = null,
    val computerChoice: GameChoice? = null,
    val roundHistory: List<RoundHistory> = emptyList(),
    val winner: String? = null,
    val isGameFinished: Boolean = false,
    val isCountdownActive: Boolean = false,
    val countdownValue: Int = 3,
    val playerType: PlayerType = PlayerType.COMPUTER,
    val gameMode: GameMode = GameMode.THREE_ROUNDS
)