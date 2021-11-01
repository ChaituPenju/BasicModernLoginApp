package com.chaitupenju.basicmodernloginapp.network.api

import com.chaitupenju.basicmodernloginapp.data.LoginResponse
import com.chaitupenju.basicmodernloginapp.data.User

interface UserApi {

    suspend fun user(accessToken: String): User

    suspend fun logout(): LoginResponse
}