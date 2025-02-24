package com.example.network

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

@Module
object NetworkModule {

//    @Provides
//    @AuthRetrofit
//    fun provideAuthRetrofit(): Retrofit = Retrofit.Builder()
//        .baseUrl("https://auth.bellerage.com/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    @Provides
//    @UserRetrofit
//    fun provideUserRetrofit(): Retrofit = Retrofit.Builder()
//        .baseUrl("https://1c.bellerage.com/bo/hs/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthRetrofit

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class UserRetrofit