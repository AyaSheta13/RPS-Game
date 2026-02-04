package com.example.rpsgame.domain.usecase

import com.example.rpsgame.data.model.GameState
import com.example.rpsgame.data.repository.GameRepository
import jakarta.inject.Inject

class UpdateScoreUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend operator fun invoke(gameState: GameState) {
        repository.saveGameState(gameState)
    }
}