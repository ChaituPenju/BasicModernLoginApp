package com.chaitupenju.basicmodernloginapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chaitupenju.basicmodernloginapp.repository.AuthRepository
import com.chaitupenju.basicmodernloginapp.repository.BaseRepository
import com.chaitupenju.basicmodernloginapp.repository.UserRepository
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repository: BaseRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as UserRepository) as T
            else -> throw IllegalArgumentException("ViewModel Not Found!")
        }
    }
}