package com.alexcao.starpx.screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexcao.starpx.navigation.Account
import com.alexcao.starpx.repository.Repository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: Repository = Repository()
) : ViewModel() {
    var username by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    fun onUsernameChange(newUsername: String) {
        username = newUsername
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }

    fun login() {
        viewModelScope.launch {
            repository.login(Account(username = username, password = password))
        }
    }
}