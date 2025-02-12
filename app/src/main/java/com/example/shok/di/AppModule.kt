package com.example.shok.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.data.AuthApi
import com.example.data.BOApi
import com.example.data.token.TokenRepositoryImpl
import com.example.domain.token.TokenRepository
import com.example.features.auth_screen.DataStoreManager.customAuthInterceptor
import com.example.features.auth_screen.dataStore
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
        tokenRepository: TokenRepository
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(customAuthInterceptor(tokenRepository))
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(@Named("Auth") retrofit: Retrofit): AuthApi = retrofit.create(
        AuthApi::class.java
    )

    @Provides
    @Singleton
    fun provideBOApi(@Named("Client") retrofit: Retrofit): BOApi = retrofit.create(
        BOApi::class.java
    )

    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context =
        application.applicationContext

    @Provides
    @Singleton
    fun providePreferencesDataStore(context: Context): DataStore<Preferences> =
        context.dataStore

    @Provides
    @Singleton
    fun provideTokenRepository(dataStore: DataStore<Preferences>): TokenRepository =
        TokenRepositoryImpl(dataStore)
}
