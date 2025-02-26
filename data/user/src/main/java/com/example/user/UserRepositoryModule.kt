package com.example.user

import com.example.auth.AuthRepository
import com.example.settings.NetworkSettings
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