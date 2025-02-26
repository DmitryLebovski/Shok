package com.example.auth

import com.example.settings.NetworkSettings
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
object AuthRepositoryModule {
    @Provides
    @Named("auth")
    fun provideAuthSettings(): NetworkSettings {
        return NetworkSettings("https://auth.bellerage.com/")
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        @Named("auth") networkSettings: NetworkSettings
    ): AuthRepository =
        AuthRepositoryImpl(networkSettings)
}