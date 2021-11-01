package com.chaitupenju.basicmodernloginapp.network.api

import com.chaitupenju.basicmodernloginapp.data.LoginResponse

interface AuthApi {

    suspend fun login(username: String, password: String): LoginResponse

}