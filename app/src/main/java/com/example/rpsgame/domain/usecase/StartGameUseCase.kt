package com.example.rpsgame.domain.usecase

import com.example.rpsgame.data.repository.GameRepository
import jakarta.inject.Inject

class StartGameUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend operator fun invoke(
        totalRounds: Int,
        playerType: String
    ) {
        val currentState = repository.getGameState()
        val updatedState = currentState.copy(
            totalRounds = totalRounds,
            playerType = if (playerType == "computer")
                com.example.rpsgame.data.model.PlayerType.COMPUTER
            else
                com.example.rpsgame.data.model.PlayerType.FRIEND,
            currentRound = 1,
            playerScore = 0,
            computerScore = 0,
            roundHistory = emptyList(),
            isGameFinished = false
        )
        repository.saveGameState(updatedState)
    }
}