package com.chaitupenju.basicmodernloginapp.network

import com.chaitupenju.basicmodernloginapp.data.LoginRequest
import com.chaitupenju.basicmodernloginapp.data.LoginResponse
import com.chaitupenju.basicmodernloginapp.data.User
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class AuthApiImpl(
    private val client: HttpClient = KtorClient()
): AuthApi {

    override suspend fun login(username: String, password: String): LoginResponse {
        return client.post {
            url(HttpRoutes.LOGIN)
            contentType(ContentType.Application.Json)
            body = LoginRequest(username, password)
        }
    }
}