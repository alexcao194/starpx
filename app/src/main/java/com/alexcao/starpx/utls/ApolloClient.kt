package com.alexcao.starpx.utls

import ImageSet
import ImageSetSummariesResult
import android.util.Log
import com.alexcao.starpx.GetImageSetSummariesQuery
import com.alexcao.starpx.type.ImageDetail
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

val okHttpClient = OkHttpClient.Builder()
    .addInterceptor { chain ->
        val original = chain.request()
        val requestBuilder: Request.Builder = original.newBuilder()
            .header("Content-Type", "application/json")
            .header("Authorization", authToken)
        val request: Request = requestBuilder.build()
        chain.proceed(request)
    }
    .build()

val apolloClient = ApolloClient.Builder()
    .serverUrl("https://api-dev.starpx.com/graphql")
    .okHttpClient(OkHttpClient.Builder().build())
    .build()
