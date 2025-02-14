package com.example.shok.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.auth.AuthRepository
import com.example.auth.AuthRepositoryImpl
import com.example.auth.AuthApi
import dagger.Module
import dagger.Provides

@Module
object AuthViewModelModule {
    @Provides
    fun provideAuthRepository(api: AuthApi, dataStore: DataStore<Preferences>): AuthRepository =
        AuthRepositoryImpl(api, dataStore)
}