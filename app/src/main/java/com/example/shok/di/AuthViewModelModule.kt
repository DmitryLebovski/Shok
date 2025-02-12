package com.example.shok.di

import com.example.data.AuthApi
import com.example.data.auth.AuthRepositoryImpl
import com.example.domain.auth.AuthRepository
import dagger.Module
import dagger.Provides

@Module
object AuthViewModelModule {
    @Provides
    fun provideAuthRepository(api: AuthApi): AuthRepository =
        AuthRepositoryImpl(api)
}