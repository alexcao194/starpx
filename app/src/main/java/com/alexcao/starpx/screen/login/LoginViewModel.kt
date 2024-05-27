package com.alexcao.starpx.screen.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.alexcao.starpx.model.Account
import com.alexcao.starpx.repository.Repository
import com.alexcao.starpx.utls.RxPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository,
    private val rxPreferences: RxPreferences
) : ViewModel() {

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

    fun login() {
        Log.d("LoginViewModel", "Logging in with username: $username and password: $password")
        rxPreferences.saveUsername(username)
        rxPreferences.savePassword(password)
        repository.login(Account(username = username, password = password))
    }
}