package com.example.data.token

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.auth.Token
import com.example.domain.token.TokenRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class TokenRepositoryImpl(
    private val dataStore: DataStore<Preferences>
): TokenRepository {
    companion object {
        private val TOKEN_KEY = stringPreferencesKey("auth_token")
    }

    override suspend fun getToken(): Token? {
        return dataStore.data.map { preferences ->
            preferences[TOKEN_KEY]?.let { Token(it) }
        }.firstOrNull()
    }


    override suspend fun saveToken(token: Token) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token.accessToken ?: "empty"
        }
    }
}