package com.chaitupenju.basicmodernloginapp.repository

import com.chaitupenju.basicmodernloginapp.utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class BaseRepository {

    protected suspend fun <T> wrapResponse(
        apiCall: suspend () -> T
    ): Response<T> = withContext(Dispatchers.IO) {
        try {
            Response.Success(apiCall.invoke())
        } catch(exception: Exception) {
            Response.Error(errorMessage = exception.localizedMessage ?: "Something went Wrong!")
        }
    }
}