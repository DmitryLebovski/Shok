package com.example.shok.di

import com.example.user.UserApi
import com.example.user.UserRepositoryImpl
import com.example.user.UserRepository
import dagger.Module
import dagger.Provides

@Module
object UserViewModelModule {
    @Provides
    fun provideUserRepository(api: UserApi): UserRepository =
        UserRepositoryImpl(api)
}

