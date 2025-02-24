package com.example.notifications

import com.example.network.UserRetrofit
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object NotificationRepositoryModule {
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://1c.bellerage.com/bo/hs/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideNotificationRepository(retrofit: Retrofit): NotificationsRepository =
        NotificationsRepositoryImpl(retrofit)
}