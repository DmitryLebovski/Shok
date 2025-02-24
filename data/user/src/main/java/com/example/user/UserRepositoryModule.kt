package com.example.user

import com.example.network.UserRetrofit
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object UserRepositoryModule {
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://1c.bellerage.com/bo/hs/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideUserRepository(retrofit: Retrofit): UserRepository =
        UserRepositoryImpl(retrofit)
}