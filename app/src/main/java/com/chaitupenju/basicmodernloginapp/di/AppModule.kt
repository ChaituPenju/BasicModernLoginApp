package com.chaitupenju.basicmodernloginapp.di

import android.content.Context
import com.chaitupenju.basicmodernloginapp.data.UserPreferences
import com.chaitupenju.basicmodernloginapp.network.KtorClient
import com.chaitupenju.basicmodernloginapp.network.api.AuthApi
import com.chaitupenju.basicmodernloginapp.network.api.AuthApiImpl
import com.chaitupenju.basicmodernloginapp.network.api.UserApi
import com.chaitupenju.basicmodernloginapp.network.api.UserApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideAuthApi(client: HttpClient): AuthApi = AuthApiImpl(client)

    @Provides
    fun provideUserApi(client: HttpClient): UserApi = UserApiImpl(client)

    @Provides
    fun provideUserPreferences(@ApplicationContext context: Context) = UserPreferences(context)

    @Provides
    fun provideKtorHttpClient() = KtorClient()

}