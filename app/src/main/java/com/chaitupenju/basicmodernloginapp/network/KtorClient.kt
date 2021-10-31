package com.chaitupenju.basicmodernloginapp.network

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*

object KtorClient {

    operator fun invoke(accessToken: String? = null): HttpClient {
        return HttpClient(Android) {
            install(JsonFeature) {
                serializer = GsonSerializer()
            }

            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("AuthAPI", message)
                    }
                }
            }

            engine {
                connectTimeout = 100_000
                socketTimeout = 100_000
            }
        }
    }
}