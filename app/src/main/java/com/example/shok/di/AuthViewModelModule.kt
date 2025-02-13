package com.example.shok.di

import com.example.auth.AuthRepository
import com.example.auth.AuthRepositoryImpl
import com.example.auth.AuthApi
import dagger.Module
import dagger.Provides

@Module
object AuthViewModelModule {
    @Provides
    fun provideAuthRepository(api: AuthApi): AuthRepository =
        AuthRepositoryImpl(api)
}