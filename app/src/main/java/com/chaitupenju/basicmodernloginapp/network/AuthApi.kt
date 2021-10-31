package com.chaitupenju.basicmodernloginapp.network

import com.chaitupenju.basicmodernloginapp.data.User
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*

interface AuthApi {

    suspend fun login(username: String, password: String): User

    companion object {
        fun create(): AuthApi {
            return AuthApiImpl(
                HttpClient(Android) {
                    install(JsonFeature) {
                        serializer = GsonSerializer()
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