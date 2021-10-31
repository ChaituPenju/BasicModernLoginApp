package com.chaitupenju.basicmodernloginapp.network

import com.chaitupenju.basicmodernloginapp.data.User
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class UserApiImpl(
    private val client: HttpClient = KtorClient()
): UserApi {

    override suspend fun user(accessToken: String): User {
        return client.get {
            url(HttpRoutes.USER)
            contentType(ContentType.Application.Json)
            header("x-access-token", accessToken)
        }
    }
}