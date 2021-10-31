package com.chaitupenju.basicmodernloginapp.network

import com.chaitupenju.basicmodernloginapp.data.User

interface UserApi {

    suspend fun user(accessToken: String): User

}