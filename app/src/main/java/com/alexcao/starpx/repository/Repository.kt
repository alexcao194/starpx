package com.alexcao.starpx.repository

import com.alexcao.starpx.navigation.Account
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.config.AWSConfiguration

class Repository {
    suspend fun login(account: Account) {
       AWSMobileClient.getInstance()
    }
}