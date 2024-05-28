package com.alexcao.starpx.repository

import android.content.Context
import com.alexcao.starpx.model.Account
import com.alexcao.starpx.utls.AWSClient
import com.alexcao.starpx.utls.RxPreferences
import javax.inject.Inject

class Repository @Inject constructor(
    private val awsClient: AWSClient,
    private val context: Context,
    private val rxPreferences: RxPreferences
) {
    companion object {
        const val TAG = "Repository"
    }

    suspend fun login(account: Account) {
        val username = account.username
        val password = account.password
        val session = awsClient.loginWithAWS(context, username, password)
        val jwt = session?.accessToken?.jwtToken ?: throw Exception("Failed to login")
        rxPreferences.saveJwt(jwt)
        return;
    }
}