package com.chaitupenju.basicmodernloginapp.network

import android.util.Log
import com.chaitupenju.basicmodernloginapp.data.User
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*

interface AuthApi {

    suspend fun login(username: String, password: String): User

    companion object {
        fun create(): AuthApi {
            return AuthApiImpl(
                HttpClient(Android) {
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
            )
        }
    }
}