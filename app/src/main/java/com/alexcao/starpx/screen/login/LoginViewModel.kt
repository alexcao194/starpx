package com.alexcao.starpx.screen.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.alexcao.starpx.model.Account
import com.alexcao.starpx.repository.Repository

class LoginViewModel(
    private val repository: Repository = Repository(),
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
        Log.d("LoginViewModel", "Logging in with username: $username and password: $password")
        repository.login(Account(username = username, password = password))
    }
}