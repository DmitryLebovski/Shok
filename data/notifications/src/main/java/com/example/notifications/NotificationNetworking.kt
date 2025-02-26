package com.example.notifications

import com.example.auth.AuthRepository
import com.example.settings.NetworkSettings
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.atomic.AtomicReference

object NotificationNetworking {
    private var notificationApi: AtomicReference<NotificationApi?> = AtomicReference(null)

    fun getNotificationApi(settings: NetworkSettings, authRepository: AuthRepository): NotificationApi {
        val currentApi = notificationApi.get()

        if (currentApi == null) {
            val authInterceptor = Interceptor { chain ->
                val token = runBlocking {
                    authRepository.getToken().getOrNull()?.accessToken ?: ""
                }
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
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(NotificationApi::class.java)

            notificationApi.set(newApi)
            return newApi
        }
        return currentApi
    }
}