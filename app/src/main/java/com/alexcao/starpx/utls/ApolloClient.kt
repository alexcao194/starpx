package com.alexcao.starpx.utls

import com.apollographql.apollo3.ApolloClient

val apolloClient = ApolloClient.Builder()
    .serverUrl("YOUR_GRAPHQL_ENDPOINT")
    .build()
