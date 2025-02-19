package com.example.auth

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val dataStore: DataStore<Preferences>
): AuthRepository {
    companion object {
        private val TOKEN_KEY = stringPreferencesKey("auth_token")
    }

    override suspend fun getTokenFromApi(code: String): Result<Token?> {
        return try {
            val response = api.postTokenByCode(code = code)
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(Throwable(response.code().toString()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getToken(): Token? {
        return dataStore.data.map { preferences ->
            preferences[TOKEN_KEY]?.let { Token(it) }
        }.firstOrNull()
    }


    override suspend fun saveToken(token: Token) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token.access_token
        }
    }
}