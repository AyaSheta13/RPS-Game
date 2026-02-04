package com.example.rpsgame.data.datasource

import com.example.rpsgame.data.model.GameState
import kotlinx.coroutines.flow.Flow

interface GameDataSource {
    suspend fun saveGameState(gameState: GameState)
    suspend fun getGameState(): GameState
    suspend fun clearGameState()
    fun getGameStateFlow(): Flow<GameState>
}

class LocalGameDataSource : GameDataSource {
    private var currentGameState: GameState = GameState()

    override suspend fun saveGameState(gameState: GameState) {
        currentGameState = gameState
    }

    override suspend fun getGameState(): GameState {
        return currentGameState
    }

    override suspend fun clearGameState() {
        currentGameState = GameState()
    }

    override fun getGameStateFlow(): Flow<GameState> {
        return kotlinx.coroutines.flow.flowOf(currentGameState)
    }
}