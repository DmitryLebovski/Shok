package com.example.shok.di

import com.example.auth.AuthRepository
import com.example.notifications.NotificationsRepository
import com.example.notifications.NotificationsRepositoryImpl
import com.example.settings.NetworkSettings
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
object NotificationRepositoryModule {
    @Provides
    @Named("notification")
    fun provideNotificationSettings(): NetworkSettings {
        return NetworkSettings("https://1c.bellerage.com/bo/hs/")
    }

    @Provides
    fun provideNotificationRepository(
        authRepository: AuthRepository,
        @Named("notification") networkSettings: NetworkSettings
    ): NotificationsRepository {
        return NotificationsRepositoryImpl(
            networkSettings,
            authRepository
        )
    }
}