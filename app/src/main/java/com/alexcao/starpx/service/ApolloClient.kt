package com.alexcao.starpx.service

import com.apollographql.apollo3.ApolloClient

val apolloClient = ApolloClient.Builder()
    .serverUrl("YOUR_GRAPHQL_ENDPOINT")
    .build()
