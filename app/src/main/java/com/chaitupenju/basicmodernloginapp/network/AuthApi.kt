package com.chaitupenju.basicmodernloginapp.network

import com.chaitupenju.basicmodernloginapp.data.LoginResponse

interface AuthApi {

    suspend fun login(username: String, password: String): LoginResponse

}