package com.alexcao.starpx.service

import android.content.Context
import android.util.Log
import com.amazonaws.mobile.client.Callback
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.client.UserStateDetails
import com.amazonaws.mobile.config.AWSConfiguration
import org.json.JSONObject

val awsConfiguration = AWSConfiguration(
    // aws cognito client id = 34fbjieukpdaq7m6q35ge10ei
    // aws cognito pool id = eu-central-1_OW0g61kEk
    JSONObject(
        """
        {
            "UserAgent": "aws-amplify/0.1.x",
            "Version": "1.0",
            "IdentityManager": {
                "Default": {}
            },
            "CredentialsProvider": {
                "CognitoIdentity": {
                    "Default": {
                        "PoolId": "eu-central-1:OW0g61kEk",
                        "Region": "eu-central-1"
                    }
                }
            },
            "CognitoUserPool": {
                "Default": {
                    "PoolId": "eu-central-1_OW0g61kEk",
                    "AppClientId": "34fbjieukpdaq7m6q35ge10ei"
                }
            }
        }
        """.trimIndent()
    )
)

fun initializeAWSMobileClient(context: Context) {

    val callback: Callback<UserStateDetails> = object : Callback<UserStateDetails> {
        override fun onResult(result: UserStateDetails) {
            Log.d("AWSMobileClient","AWSMobileClient initialized")
        }

        override fun onError(e: Exception) {
            Log.e("AWSMobileClient", e.toString())
        }
    }

    AWSMobileClient.getInstance().initialize(context, awsConfiguration, callback)
}