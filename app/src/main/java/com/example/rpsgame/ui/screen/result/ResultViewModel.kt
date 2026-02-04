package com.example.rpsgame.ui.screen.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.rpsgame.data.model.GameState
import com.example.rpsgame.data.repository.GameRepository
import com.example.rpsgame.ui.navigation.NavRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val repository: GameRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ResultUiState())
    val uiState: StateFlow<ResultUiState> = _uiState.asStateFlow()

    private var isInitialized = false

    fun loadGameResult() {
        if (isInitialized) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val gameState = repository.getGameState()

            if (!gameState.isGameFinished) {
                repository.clearGameState()
                _uiState.update { it.copy(isLoading = false) }
                return@launch
            }

            _uiState.update {
                it.copy(
                    gameState = gameState,
                    isLoading = false
                )
            }

            isInitialized = true
        }
    }

    fun playAgain(navController: NavController) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val resetState = GameState(
                totalRounds = uiState.value.gameState.totalRounds,
                playerType = uiState.value.gameState.playerType,
                gameMode = uiState.value.gameState.gameMode
            )
            repository.saveGameState(resetState)

            _uiState.update { it.copy(isLoading = false) }

            navController.navigate(
                NavRoutes.buildGameRoute(
                    uiState.value.gameState.totalRounds,
                    uiState.value.gameState.playerType.name.lowercase()
                )
            ) {
                popUpTo(NavRoutes.RESULT) { inclusive = true }
            }
        }
    }

    fun navigateToMain(navController: NavController) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            repository.clearGameState()
            _uiState.update { it.copy(isLoading = false) }

            navController.popBackStack(NavRoutes.MAIN, false)
        }
    }
}