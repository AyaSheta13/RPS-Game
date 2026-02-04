package com.example.rpsgame.di

import com.example.rpsgame.data.datasource.GameDataSource
import com.example.rpsgame.data.datasource.LocalGameDataSource
import com.example.rpsgame.data.repository.GameRepository
import com.example.rpsgame.data.repository.GameRepositoryImpl
import com.example.rpsgame.domain.engine.GameEngine
import com.example.rpsgame.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGameDataSource(): GameDataSource {
        return LocalGameDataSource()
    }

    @Provides
    @Singleton
    fun provideGameRepository(dataSource: GameDataSource): GameRepository {
        return GameRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideGameEngine(): GameEngine {
        return GameEngine()
    }

    @Provides
    @Singleton
    fun provideGetComputerChoiceUseCase(gameEngine: GameEngine): GetComputerChoiceUseCase {
        return GetComputerChoiceUseCase(gameEngine)
    }

    @Provides
    @Singleton
    fun provideCalculateWinnerUseCase(gameEngine: GameEngine): CalculateWinnerUseCase {
        return CalculateWinnerUseCase(gameEngine)
    }

    @Provides
    @Singleton
    fun provideStartGameUseCase(repository: GameRepository): StartGameUseCase {
        return StartGameUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateScoreUseCase(repository: GameRepository): UpdateScoreUseCase {
        return UpdateScoreUseCase(repository)
    }
}