package com.example.shok.di

import com.example.auth.AuthRepository
import com.example.settings.NetworkSettings
import com.example.user.UserRepository
import com.example.user.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
object UserRepositoryModule {
    @Provides
    @Named("user")
    fun provideUserSettings(): NetworkSettings {
        return NetworkSettings("https://1c.bellerage.com/bo/hs/")
    }

    @Provides
    fun provideUserRepository(
        authRepository: AuthRepository,
        @Named("user") networkSettings: NetworkSettings
    ): UserRepository {
        return UserRepositoryImpl(
            networkSettings,
            authRepository
        )
    }
}