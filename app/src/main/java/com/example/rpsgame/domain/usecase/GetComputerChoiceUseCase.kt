package com.example.rpsgame.domain.usecase

import com.example.rpsgame.data.model.GameChoice
import com.example.rpsgame.domain.engine.GameEngine
import javax.inject.Inject

class GetComputerChoiceUseCase @Inject constructor(
    private val gameEngine: GameEngine
) {
    operator fun invoke(): GameChoice {
        return gameEngine.getComputerChoice()
    }
}