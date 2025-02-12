package com.example.shok.di

import com.example.data.BOApi
import com.example.data.user.UserRepositoryImpl
import com.example.domain.user.UserRepository
import dagger.Module
import dagger.Provides

@Module
object UserViewModelModule {
    @Provides
    fun provideUserRepository(api: BOApi): UserRepository =
        UserRepositoryImpl(api)
}