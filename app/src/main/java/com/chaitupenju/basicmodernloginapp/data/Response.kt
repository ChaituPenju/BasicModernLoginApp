package com.chaitupenju.basicmodernloginapp.data

sealed class Response {
    object Loading: Response()
    data class Success<out T>(val data: T): Response()
    data class Error(val errorMessage: String): Response()
}
