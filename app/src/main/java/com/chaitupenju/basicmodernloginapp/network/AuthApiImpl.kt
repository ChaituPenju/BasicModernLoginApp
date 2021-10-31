package com.chaitupenju.basicmodernloginapp.network

import com.chaitupenju.basicmodernloginapp.data.LoginRequest
import com.chaitupenju.basicmodernloginapp.data.User
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class AuthApiImpl(
    private val client: HttpClient
): AuthApi {

    override suspend fun login(username: String, password: String): User {
        return client.post {
            url(HttpRoutes.LOGIN)
            contentType(ContentType.Application.Json)
            body = LoginRequest(username, password)
        }
    }
}