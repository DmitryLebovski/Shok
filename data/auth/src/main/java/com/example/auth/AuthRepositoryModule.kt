package com.example.auth

import com.example.network.AuthRetrofit
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object AuthRepositoryModule {
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://auth.bellerage.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideAuthRepository(retrofit: Retrofit): AuthRepository =
        AuthRepositoryImpl(retrofit)
}