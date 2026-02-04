package com.example.rpsgame.domain.usecase

import com.example.rpsgame.data.model.GameChoice
import com.example.rpsgame.data.model.RoundResult
import com.example.rpsgame.domain.engine.GameEngine
import jakarta.inject.Inject

class CalculateWinnerUseCase @Inject constructor(
    private val gameEngine: GameEngine
) {
    operator fun invoke(
        playerChoice: GameChoice,
        computerChoice: GameChoice
    ): RoundResult {
        return gameEngine.calculateWinner(playerChoice, computerChoice)
    }
}