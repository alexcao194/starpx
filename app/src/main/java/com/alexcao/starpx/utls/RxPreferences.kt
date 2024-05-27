package com.alexcao.starpx.utls

import android.content.SharedPreferences
import javax.inject.Inject

class RxPreferences @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    companion object {
        const val KEY_USERNAME = "username"
        const val KEY_PASSWORD = "password"
    }

    fun saveUsername(username: String) {
        sharedPreferences.edit().putString(KEY_USERNAME, username).apply()
    }

    fun savePassword(password: String) {
        sharedPreferences.edit().putString(KEY_PASSWORD, password).apply()
    }

    fun getUsername(): String {
        return sharedPreferences.getString(KEY_USERNAME, "") ?: ""
    }

    fun getPassword(): String {
        return sharedPreferences.getString(KEY_PASSWORD, "") ?: ""
    }
}