package com.example.rpsgame.data.repository

import com.example.rpsgame.data.datasource.GameDataSource
import com.example.rpsgame.data.model.GameState
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GameRepositoryImpl @Inject constructor(
    private val dataSource: GameDataSource
) : GameRepository {

    override suspend fun saveGameState(gameState: GameState) {
        dataSource.saveGameState(gameState)
    }

    override suspend fun getGameState(): GameState {
        return dataSource.getGameState()
    }

    override suspend fun clearGameState() {
        dataSource.clearGameState()
    }

    override fun getGameStateFlow(): Flow<GameState> {
        return dataSource.getGameStateFlow()
    }
}