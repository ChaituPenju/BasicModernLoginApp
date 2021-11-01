package com.chaitupenju.basicmodernloginapp.network.api

import com.chaitupenju.basicmodernloginapp.data.LoginResponse
import com.chaitupenju.basicmodernloginapp.data.User
import com.chaitupenju.basicmodernloginapp.network.HttpRoutes
import com.chaitupenju.basicmodernloginapp.network.KtorClient
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class UserApiImpl(
    private val client: HttpClient = KtorClient()
): UserApi {

    override suspend fun user(accessToken: String): User {
        return client.get {
            url(HttpRoutes.USER)
            header("x-access-token", accessToken)
        }
    }

    override suspend fun logout(): LoginResponse {
        return client.get {
            url(HttpRoutes.LOGOUT)
        }
    }
}