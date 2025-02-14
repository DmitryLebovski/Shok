package com.example.shok.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.auth.AuthApi
import com.example.auth.AuthRepository
import com.example.auth.DataStoreManager.customAuthInterceptor
import com.example.auth.dataStore
import com.example.notifications.NotificationApi
import com.example.user.UserApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
object AppModule {
    @Provides
    @Singleton
    @Named("Auth")
    fun provideRetrofitAuth(): Retrofit = Retrofit.Builder()
        .baseUrl("https://auth.bellerage.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    @Named("Client")
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://1c.bellerage.com/bo/hs/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authRepository: AuthRepository
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(customAuthInterceptor(authRepository))
            .build()
    }

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
