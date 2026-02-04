package com.example.rpsgame.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rpsgame.data.model.GameMode
import com.example.rpsgame.data.model.PlayerType
import com.example.rpsgame.domain.usecase.StartGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val startGameUseCase: StartGameUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun init() {
        // يمكن إضافة أي تهيئة مطلوبة هنا
    }

    fun selectGameMode(mode: GameMode) {
        _uiState.update { it.copy(selectedMode = mode) }
    }

    fun selectPlayerType(playerType: PlayerType) {
        _uiState.update { it.copy(playerType = playerType) }
    }

    fun startGame(onNavigate: (totalRounds: Int, playerType: String) -> Unit) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            startGameUseCase(
                totalRounds = uiState.value.selectedMode.rounds,
                playerType = uiState.value.playerType.name.lowercase()
            )
            _uiState.update { it.copy(isLoading = false) }
            onNavigate(
                uiState.value.selectedMode.rounds,
                uiState.value.playerType.name.lowercase()
            )
        }
    }
}