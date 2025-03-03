package com.example.shok.di

import android.app.Application
import android.content.Context
import com.example.notifications.GetUserNotificationsUseCase
import com.example.user.GetCombinedUserNotificationsUseCase
import com.example.user.GetUserInfoUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {
    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context =
        application.applicationContext
}

@Module
object CombinedModule {
    @Provides
    fun provideCombinedUserNotificationsUseCase(
        userInfoUseCase: GetUserInfoUseCase,
        notificationsUseCase: GetUserNotificationsUseCase
    ): GetCombinedUserNotificationsUseCase {
        return GetCombinedUserNotificationsUseCase(userInfoUseCase, notificationsUseCase)
    }
}
