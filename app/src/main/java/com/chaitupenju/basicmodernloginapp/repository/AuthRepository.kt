package com.chaitupenju.basicmodernloginapp.repository

import com.chaitupenju.basicmodernloginapp.data.Response
import com.chaitupenju.basicmodernloginapp.network.AuthApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository(
    private val api: AuthApi
) {

    suspend fun login(username: String, password: String) = wrapResponse {
        api.login(username, password)
    }


    private suspend fun <T> wrapResponse(
        apiCall: suspend () -> T
    ): Response<T> = withContext(Dispatchers.IO) {
            try {
                Response.Success(apiCall.invoke())
            } catch(exception: Exception) {
                Response.Error(errorMessage = exception.localizedMessage ?: "Something went Wrong!")
            }
        }
}