package com.example.user

import com.example.auth.AuthRepository
import com.example.settings.NetworkSettings
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.atomic.AtomicReference

object UserNetworking {
    private var userApi: AtomicReference<UserApi?> = AtomicReference(null)

    fun getUserApi(settings: NetworkSettings, authRepository: AuthRepository): UserApi {
        val currentApi = userApi.get()

        if (currentApi == null) {
            val authInterceptor = Interceptor { chain ->
                val token = runBlocking { authRepository.getToken().getOrNull()?.accessToken ?: "" }
                val request = chain.request().newBuilder()
                    .header("Authorization", "Bearer $token")
                    .build()
                chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .build()

            val newApi = Retrofit.Builder()
                .baseUrl(settings.host)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserApi::class.java)

            userApi.set(newApi)
            return newApi
        }
        return currentApi
    }
}