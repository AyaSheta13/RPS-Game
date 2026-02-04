package com.example.rpsgame.domain.engine

import com.example.rpsgame.data.model.GameChoice
import com.example.rpsgame.data.model.RoundResult
import javax.inject.Inject

class GameEngine @Inject constructor() {

    fun getComputerChoice(): GameChoice {
        return GameChoice.values().random()
    }

    fun calculateWinner(
        playerChoice: GameChoice,
        computerChoice: GameChoice
    ): RoundResult {
        return when {
            playerChoice == computerChoice -> RoundResult.DRAW
            playerChoice == GameChoice.ROCK && computerChoice == GameChoice.SCISSORS -> RoundResult.PLAYER_WIN
            playerChoice == GameChoice.PAPER && computerChoice == GameChoice.ROCK -> RoundResult.PLAYER_WIN
            playerChoice == GameChoice.SCISSORS && computerChoice == GameChoice.PAPER -> RoundResult.PLAYER_WIN
            else -> RoundResult.COMPUTER_WIN
        }
    }

    fun determineWinnerText(result: RoundResult): String {
        return when (result) {
            RoundResult.PLAYER_WIN -> "You Win!"
            RoundResult.COMPUTER_WIN -> "Computer Wins!"
            RoundResult.DRAW -> "It's a Draw!"
        }
    }

    fun isGameFinished(
        playerScore: Int,
        computerScore: Int,
        totalRounds: Int,
        currentRound: Int
    ): Boolean {
        val maxPossibleScore = totalRounds - currentRound + 1
        return playerScore > computerScore + maxPossibleScore ||
                computerScore > playerScore + maxPossibleScore ||
                currentRound > totalRounds
    }
}