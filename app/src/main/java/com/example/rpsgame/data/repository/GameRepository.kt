package com.example.rpsgame.data.repository

import com.example.rpsgame.data.model.GameState
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    suspend fun saveGameState(gameState: GameState)
    suspend fun getGameState(): GameState
    suspend fun clearGameState()
    fun getGameStateFlow(): Flow<GameState>
}