package com.alexcao.starpx.utls

import android.content.SharedPreferences
import javax.inject.Inject

class RxPreferences @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    companion object {
        const val KEY_USERNAME = "username"
        const val KEY_PASSWORD = "password"
        const val KEY_TOKEN = "token"
        const val KEY_NEXT_TOKEN = "nextToken"
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

    fun saveJwt(jwt: String) {
        sharedPreferences.edit().putString(KEY_TOKEN, jwt).apply()
    }

    fun getJwt(): String? {
        return sharedPreferences.getString(KEY_TOKEN, "")
    }

    fun getNextToken(): String? {
        return sharedPreferences.getString(KEY_NEXT_TOKEN, null)
    }

    fun saveNextToken(token: String?) {
        sharedPreferences.edit().putString(KEY_NEXT_TOKEN, token).apply()
    }
}