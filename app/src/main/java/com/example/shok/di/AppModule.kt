package com.example.shok.di

import android.app.Application
import android.content.Context
import com.example.data.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofitAuth(): Retrofit = Retrofit.Builder()
        .baseUrl("https://auth.bellerage.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideRetrofitClient(): Retrofit = Retrofit.Builder()
        .baseUrl("https://1c.bellerage.com/bo/hs/")
        .addConverterFactory(GsonConverterFactory.create())
//        .client(
//            OkHttpClient.Builder()
//            .addInterceptor(customAuthInterceptor())
//            .build())
        .build()

    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): AuthApi = retrofit.create(
        AuthApi::class.java
    )

    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context =
        application.applicationContext
}


//object DataStoreManager {
//    fun customAuthInterceptor(): Interceptor {
//        return Interceptor { chain ->
//            val token = "IDK" //TODO()
//            val requestBuilder = chain.request().newBuilder()
//            if (!token.isNullOrEmpty()) {
//                requestBuilder.addHeader("Authorization", "Bearer $token")
//            }
//            chain.proceed(requestBuilder.build())
//        }
//    }
//}
