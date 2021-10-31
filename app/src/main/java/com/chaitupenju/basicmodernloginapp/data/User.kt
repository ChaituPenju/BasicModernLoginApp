package com.chaitupenju.basicmodernloginapp.data

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int,
    val name: String,
    val email: String,
    @SerializedName("access_token")
    val accessToken: String
) {
    fun convertToString(): String {
        return "ID : $id\n" +
               "Name : $name\n" +
               "Email : $email"
    }
}
