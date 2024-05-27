package com.alexcao.starpx.repository

import android.util.Log
import com.alexcao.starpx.navigation.Account
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.client.Callback
import com.amazonaws.mobile.client.results.SignInResult
import com.amazonaws.mobile.config.AWSConfiguration

class Repository {
    fun login(account: Account) {
        val callback = object : Callback<SignInResult> {
            override fun onResult(result: SignInResult) {
                Log.d("SignInResult", result.toString())
            }

            override fun onError(e: Exception) {
                Log.e("SignInResult", e.toString())
            }
        }
       AWSMobileClient.getInstance().signIn(account.username, account.password, null, callback)
    }
}