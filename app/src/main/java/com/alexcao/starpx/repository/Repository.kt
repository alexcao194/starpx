package com.alexcao.starpx.repository

import android.content.Context
import android.util.Log
import com.alexcao.starpx.GetImageSetSummariesQuery
import com.alexcao.starpx.model.Account
import com.alexcao.starpx.utls.AWSClient
import com.alexcao.starpx.utls.RxPreferences
import com.alexcao.starpx.utls.apolloClient
import java.util.Optional
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
        Log.d(TAG, "login: $jwt")

        val response = apolloClient.query(GetImageSetSummariesQuery(
            customerId = "aabb1234"
        )).execute()
        val data = response.data
        val imageSetSummaries = data?.getImageSetSummaries
        val token = imageSetSummaries?.nextToken
        Log.d("Next Token", "$token")

        rxPreferences.saveJwt(jwt)
        return;
    }
}