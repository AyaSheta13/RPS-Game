package com.example.rpsgame.data.model

data class RoundHistory(
    val roundNumber: Int,
    val playerChoice: GameChoice,
    val computerChoice: GameChoice,
    val result: RoundResult
)