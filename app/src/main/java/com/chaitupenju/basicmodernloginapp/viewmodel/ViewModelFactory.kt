package com.chaitupenju.basicmodernloginapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chaitupenju.basicmodernloginapp.repository.AuthRepository
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repository: AuthRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository) as T
            else -> throw IllegalArgumentException("ViewModel Not Found!")
        }
    }
}