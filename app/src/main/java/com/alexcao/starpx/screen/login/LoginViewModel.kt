package com.alexcao.starpx.screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
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
        // Perform login logic here
    }
}