package com.example.shok.di

import com.example.notifications.NotificationApi
import com.example.notifications.NotificationsRepository
import com.example.notifications.NotificationsRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
object NotificationViewModelModule {
    @Provides
    fun provideNotificationRepository(api: NotificationApi): NotificationsRepository =
        NotificationsRepositoryImpl(api)
}