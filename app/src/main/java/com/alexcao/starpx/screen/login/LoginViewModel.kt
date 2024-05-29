package com.alexcao.starpx.screen.login

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexcao.starpx.model.Account
import com.alexcao.starpx.repository.Repository
import com.alexcao.starpx.utls.RxPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository,
    private val rxPreferences: RxPreferences
) : ViewModel() {
    private val _loginUiState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState.asStateFlow()
    var username by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    init {
        val savedUsername = rxPreferences.getUsername()
        val savedPassword = rxPreferences.getPassword()
        username = savedUsername
        password = savedPassword
    }

    fun onUsernameChange(newUsername: String) {
        username = newUsername
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }

    fun clearError() {
        _loginUiState.update { it.copy(error = null) }
    }

    fun login() {
        Log.d("LoginViewModel", "Logging in with username: $username and password: $password")
        rxPreferences.saveUsername(username)
        rxPreferences.savePassword(password)
        viewModelScope.launch {
            _loginUiState.update { it.copy(isLoading = true) }
            try {
                repository.login(Account(username = username, password = password))
            } catch (e: Exception) {
                _loginUiState.update { it.copy(isLoading = false, error = e.message ?: "Login failed") }
                return@launch
            }
            _loginUiState.update { it.copy(isLoading = false, isSuccessful = true) }
        }
    }
}