package com.chaitupenju.basicmodernloginapp.network.api

import com.chaitupenju.basicmodernloginapp.data.LoginRequest
import com.chaitupenju.basicmodernloginapp.data.LoginResponse
import com.chaitupenju.basicmodernloginapp.network.HttpRoutes
import com.chaitupenju.basicmodernloginapp.network.KtorClient
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

class AuthApiImpl(
    private val client: HttpClient = KtorClient()
): AuthApi {

    override suspend fun login(username: String, password: String): LoginResponse {
        return try {
            client.post {
                url(HttpRoutes.LOGIN)
                body = LoginRequest(username, password)
            }
        } catch (e: ClientRequestException) {
            throw Exception((e.response.receive() as LoginResponse).message)
        }
    }
}