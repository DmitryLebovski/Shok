package com.example.shok.di

import android.util.Log
import com.example.auth.AuthRepository
import com.example.shok.BuildConfig
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Singleton
    @Named("Auth")
    fun provideRetrofitAuth(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL_AUTH)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    @Named("Client")
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL_CLIENT)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(authRepository: AuthRepository): Interceptor {
        return Interceptor { chain ->
            val token = runBlocking {
                authRepository.getToken()
            }
            val requestBuilder = chain.request().newBuilder()
            Log.d("CODEXTOKENINTERCEPTOR", token.toString())

            if (!token?.access_token.isNullOrEmpty()) {
                requestBuilder.addHeader("Authorization", "Bearer ${token?.access_token}")
            }
            chain.proceed(requestBuilder.build())
        }
    }
}