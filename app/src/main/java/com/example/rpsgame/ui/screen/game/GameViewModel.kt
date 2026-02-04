package com.example.rpsgame.ui.screen.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rpsgame.data.model.*
import com.example.rpsgame.domain.usecase.CalculateWinnerUseCase
import com.example.rpsgame.domain.usecase.GetComputerChoiceUseCase
import com.example.rpsgame.domain.usecase.StartGameUseCase
import com.example.rpsgame.domain.usecase.UpdateScoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val getComputerChoiceUseCase: GetComputerChoiceUseCase,
    private val calculateWinnerUseCase: CalculateWinnerUseCase,
    private val startGameUseCase: StartGameUseCase,
    private val updateScoreUseCase: UpdateScoreUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private var currentPlayerInFriendMode = 1
    private var player1Choice: GameChoice? = null
    private var player2Choice: GameChoice? = null

    fun startGame(totalRounds: Int, playerType: PlayerType) {
        viewModelScope.launch {
            startGameUseCase(totalRounds, playerType.name.lowercase())

            _uiState.update {
                it.copy(
                    totalRounds = totalRounds,
                    playerType = playerType,
                    currentRound = 1,
                    playerScore = 0,
                    computerScore = 0,
                    roundHistory = emptyList(),
                    isGameActive = true,
                    isFriendModeSecondPlayer = false,
                    playerChoiceRevealed = false,
                    computerChoiceRevealed = false,
                    showChoiceButtons = true
                )
            }

            currentPlayerInFriendMode = 1
            player1Choice = null
            player2Choice = null
        }
    }

    fun selectChoice(choice: GameChoice) {
        if (_uiState.value.isCountdownActive || _uiState.value.isRevealingChoices) return

        if (_uiState.value.playerType == PlayerType.FRIEND) {
            handleFriendModeChoice(choice)
        } else {
            _uiState.update {
                it.copy(
                    playerChoice = choice,
                    computerChoice = null,
                    resultText = null,
                    isChoiceSelected = true,
                    isFriendModeSecondPlayer = false,
                    playerChoiceRevealed = false,
                    showChoiceButtons = false
                )
            }

            startCountdown {
                revealChoices()
            }
        }
    }

    private fun handleFriendModeChoice(choice: GameChoice) {
        when (currentPlayerInFriendMode) {
            1 -> {
                player1Choice = choice
                _uiState.update {
                    it.copy(
                        playerChoice = choice,
                        resultText = null,
                        isChoiceSelected = false,
                        isFriendModeSecondPlayer = true,
                        playerChoiceRevealed = false,
                        computerChoiceRevealed = false,
                        showChoiceButtons = true
                    )
                }
                currentPlayerInFriendMode = 2
            }

            2 -> {
                player2Choice = choice
                _uiState.update {
                    it.copy(
                        computerChoice = choice,
                        resultText = null,
                        isFriendModeSecondPlayer = false,
                        computerChoiceRevealed = false,
                        showChoiceButtons = false
                    )
                }

                viewModelScope.launch {
                    delay(500)
                    startCountdown {
                        revealChoicesForFriendMode()
                    }
                }
            }
        }
    }

    private fun startCountdown(onFinish: () -> Unit) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isCountdownActive = true,
                    countdownValue = 3,
                    showChoiceButtons = false
                )
            }

            delay(300)
            _uiState.update { it.copy(countdownValue = 3) }
            delay(500)

            _uiState.update { it.copy(countdownValue = 2) }
            delay(800)

            _uiState.update { it.copy(countdownValue = 1) }
            delay(800)

            _uiState.update { it.copy(countdownValue = 0) }
            delay(600)

            _uiState.update { it.copy(isCountdownActive = false) }

            onFinish()
        }
    }

    private fun revealChoicesForFriendMode() {
        viewModelScope.launch {
            _uiState.update { it.copy(isRevealingChoices = true) }

            val choice1 = player1Choice ?: return@launch
            val choice2 = player2Choice ?: return@launch

            _uiState.update {
                it.copy(
                    playerChoice = choice1,
                    computerChoice = choice2,
                    playerChoiceRevealed = true,
                    computerChoiceRevealed = true
                )
            }

            delay(500)

            val result = calculateWinnerUseCase(choice1, choice2)
            val resultText = when (result) {
                RoundResult.PLAYER_WIN -> "Player 1 Wins!"
                RoundResult.COMPUTER_WIN -> "Player 2 Wins!"
                RoundResult.DRAW -> "It's a Draw!"
            }

            val newPlayerScore =
                _uiState.value.playerScore + if (result == RoundResult.PLAYER_WIN) 1 else 0
            val newComputerScore =
                _uiState.value.computerScore + if (result == RoundResult.COMPUTER_WIN) 1 else 0

            val newHistory = _uiState.value.roundHistory.toMutableList()
            newHistory.add(
                RoundHistory(
                    roundNumber = _uiState.value.currentRound,
                    playerChoice = choice1,
                    computerChoice = choice2,
                    result = result
                )
            )

            _uiState.update {
                it.copy(
                    resultText = resultText,
                    playerScore = newPlayerScore,
                    computerScore = newComputerScore,
                    roundHistory = newHistory,
                    currentRound = it.currentRound + 1
                )
            }

            delay(2000)

            if (_uiState.value.currentRound > _uiState.value.totalRounds) {
                finishGame()
            } else {
                resetForNextRound()
            }
        }
    }

    private fun revealChoices() {
        viewModelScope.launch {
            _uiState.update { it.copy(isRevealingChoices = true) }

            val computerChoice = getComputerChoiceUseCase()
            val playerChoice = _uiState.value.playerChoice ?: return@launch

            _uiState.update {
                it.copy(
                    computerChoice = computerChoice,
                    playerChoiceRevealed = true,
                    computerChoiceRevealed = true
                )
            }

            delay(500)

            val result = calculateWinnerUseCase(playerChoice, computerChoice)
            val resultText = when (result) {
                RoundResult.PLAYER_WIN -> "You Win!"
                RoundResult.COMPUTER_WIN -> "Computer Wins!"
                RoundResult.DRAW -> "It's a Draw!"
            }

            val newPlayerScore =
                _uiState.value.playerScore + if (result == RoundResult.PLAYER_WIN) 1 else 0
            val newComputerScore =
                _uiState.value.computerScore + if (result == RoundResult.COMPUTER_WIN) 1 else 0

            val newHistory = _uiState.value.roundHistory.toMutableList()
            newHistory.add(
                RoundHistory(
                    roundNumber = _uiState.value.currentRound,
                    playerChoice = playerChoice,
                    computerChoice = computerChoice,
                    result = result
                )
            )

            _uiState.update {
                it.copy(
                    resultText = resultText,
                    playerScore = newPlayerScore,
                    computerScore = newComputerScore,
                    roundHistory = newHistory,
                    currentRound = it.currentRound + 1
                )
            }

            delay(2000)

            if (_uiState.value.currentRound > _uiState.value.totalRounds) {
                finishGame()
            } else {
                resetForNextRound()
            }
        }
    }

    private fun resetForNextRound() {
        if (_uiState.value.playerType == PlayerType.FRIEND) {
            currentPlayerInFriendMode = 1
            player1Choice = null
            player2Choice = null
        }

        _uiState.update {
            it.copy(
                playerChoice = null,
                computerChoice = null,
                resultText = null,
                isChoiceSelected = false,
                isCountdownActive = false,
                isRevealingChoices = false,
                isFriendModeSecondPlayer = false,
                playerChoiceRevealed = false,
                computerChoiceRevealed = false,
                showChoiceButtons = true,
                countdownValue = 3
            )
        }
    }

    private fun finishGame() {
        val winner = when {
            _uiState.value.playerScore > _uiState.value.computerScore ->
                if (_uiState.value.playerType == PlayerType.FRIEND) "Player 1" else "You"

            _uiState.value.computerScore > _uiState.value.playerScore ->
                if (_uiState.value.playerType == PlayerType.FRIEND) "Player 2" else "Computer"

            else -> "Draw"
        }

        _uiState.update {
            it.copy(
                winner = winner,
                isGameActive = false,
                isGameFinished = true,
                showChoiceButtons = false
            )
        }

        viewModelScope.launch {
            updateScoreUseCase(
                GameState(
                    playerScore = _uiState.value.playerScore,
                    computerScore = _uiState.value.computerScore,
                    currentRound = _uiState.value.currentRound - 1,
                    totalRounds = _uiState.value.totalRounds,
                    playerChoice = _uiState.value.playerChoice,
                    computerChoice = _uiState.value.computerChoice,
                    roundHistory = _uiState.value.roundHistory,
                    winner = winner,
                    isGameFinished = true,
                    playerType = _uiState.value.playerType,
                    gameMode = when (_uiState.value.totalRounds) {
                        3 -> GameMode.THREE_ROUNDS
                        5 -> GameMode.FIVE_ROUNDS
                        10 -> GameMode.TEN_ROUNDS
                        else -> GameMode.THREE_ROUNDS
                    }
                )
            )
        }
    }

    fun resetGame() {
        startGame(_uiState.value.totalRounds, _uiState.value.playerType)
    }
}
