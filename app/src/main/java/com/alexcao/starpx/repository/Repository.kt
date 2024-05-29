@file:OptIn(ApolloExperimental::class)

package com.alexcao.starpx.repository

import android.content.Context
import android.util.Log
import com.alexcao.starpx.GetImageSetSummariesQuery
import com.alexcao.starpx.model.Account
import com.alexcao.starpx.model.GetImageSetSummariesResponse
import com.alexcao.starpx.model.ImageSet
import com.alexcao.starpx.utls.AWSClient
import com.alexcao.starpx.utls.RxPreferences
import com.alexcao.starpx.utls.getApolloClient
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.annotations.ApolloExperimental
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.api.toJsonString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class Repository @Inject constructor(
    private val awsClient: AWSClient,
    private val context: Context,
    private val rxPreferences: RxPreferences
) {

    private lateinit var apolloClient: ApolloClient

    companion object {
        const val TAG = "Repository"
    }

    suspend fun login(account: Account) {
        val username = account.username
        val password = account.password
        val session = awsClient.loginWithAWS(context, username, password)
        val jwt = session?.accessToken?.jwtToken ?: throw Exception("Failed to login")
        Log.d(TAG, "login: $jwt")
        rxPreferences.saveJwt(jwt)
        apolloClient = getApolloClient(token = jwt)
        return;
    }

    suspend fun getImages(): List<ImageSet> {
        val response = apolloClient.query(
            GetImageSetSummariesQuery(
                customerId = "aabb1234",
                limit = Optional.present(30),
                nextToken = Optional.presentIfNotNull(rxPreferences.getNextToken())
            )
        ).execute()

        val jsonResponse = response.data?.toJsonString()
        jsonResponse?.let { json ->
            Log.d(TAG, "getImages: $json")
            val parsedResponse = Json.decodeFromString<GetImageSetSummariesResponse>(json)

            val nextToken = parsedResponse.getImageSetSummaries.nextToken
            rxPreferences.saveNextToken(nextToken)

            val imageSets = parsedResponse.getImageSetSummaries.imageSets
            return imageSets
        }

        return emptyList()
    }
}