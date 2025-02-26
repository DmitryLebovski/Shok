package com.example.auth

import com.example.settings.NetworkSettings
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.atomic.AtomicReference

object AuthNetworking {
    private val authApi: AtomicReference<AuthApi?> = AtomicReference(null)

    fun getAuthApi(settings: NetworkSettings): AuthApi {
        val currentApi = authApi.get()

        if (currentApi == null) {
            val newApi = Retrofit.Builder()
                .baseUrl(settings.host)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AuthApi::class.java)

            authApi.set(newApi)
            return newApi
        }
        return currentApi
    }
}

//getAndSet возвращает значение, а потом устанваливет новое