package com.alexcao.starpx.utls

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.OkHttpClient
import okhttp3.Request


private const val BASE_URL = "https://api-dev.starpx.com/graphql"
fun getApolloClient(token: String): ApolloClient {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder: Request.Builder = original.newBuilder()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }
        .build()

    val apolloClient = ApolloClient.Builder()
        .serverUrl(BASE_URL)
        .okHttpClient(okHttpClient)
        .build()

    return apolloClient
}