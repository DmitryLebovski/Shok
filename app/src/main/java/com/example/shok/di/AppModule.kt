package com.example.shok.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.auth.AuthApi
import com.example.auth.dataStore
import com.example.notifications.NotificationApi
import com.example.user.UserApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
object AppModule {
    @Provides
    @Singleton
    fun provideAuthApi(@Named("Auth") retrofit: Retrofit): AuthApi = retrofit.create(
        AuthApi::class.java
    )

    @Provides
    @Singleton
    fun provideUserApi(@Named("Client") retrofit: Retrofit): UserApi = retrofit.create(
        UserApi::class.java
    )

    @Provides
    @Singleton
    fun provideNotificationApi(@Named("Client") retrofit: Retrofit): NotificationApi = retrofit.create(
        NotificationApi::class.java
    )

    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context =
        application.applicationContext

    @Provides
    @Singleton
    fun providePreferencesDataStore(context: Context): DataStore<Preferences> =
        context.dataStore
}
