package com.chaitupenju.basicmodernloginapp.repository

import com.chaitupenju.basicmodernloginapp.network.api.UserApi

class UserRepository(
    private val api: UserApi
): BaseRepository() {

    suspend fun getUser(accessToken: String) = wrapResponse {
        api.user(accessToken)
    }

    suspend fun logout() = wrapResponse {
        api.logout()
    }
}