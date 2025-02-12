package com.example.features.auth_screen

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.preferencesDataStore
import com.example.domain.token.TokenRepository
import com.example.features.R
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor

@Composable
fun AuthScreen(
    navigate: ()-> Unit,
    component: ProviderAuthViewModel
) {
    val context = LocalContext.current
    val viewModel = remember { component.authViewModel() }

    context.getAuthCode()?.let { viewModel.setAuthCode(it) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LaunchedEffect(viewModel.authCode) {
            viewModel.authCode.value?.let { code ->
                Log.d("CODEX", code)
                context.saveAuthCode(code)
                navigate()
            }
        }

        Button(
            onClick = { context.launchCustomTabs(
                url = "https://auth.bellerage.com/realms/bellerage/protocol/openid-connect/auth?response_type=code&client_id=onec_client&redirect_uri=bellerage://mobilelogin&scope=openid",
                useIncognito = false
            ) },
            modifier = Modifier.width(160.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
        )
        { Text(stringResource(R.string.sign_in)) }
    }
}

fun Context.launchCustomTabs(url: String, useIncognito: Boolean?) {
    CustomTabsIntent.Builder().build().apply {
        if (useIncognito == true) {
            intent.putExtra(
                "com.google.android.apps.chrome.EXTRA_OPEN_NEW_INCOGNITO_TAB",
                true
            )
        }
    }
        .launchUrl(this, Uri.parse(url))
}


val Context.dataStore by preferencesDataStore("settings")
object DataStoreManager {
    fun customAuthInterceptor(tokenRepository: TokenRepository): Interceptor {
        return Interceptor { chain ->
            val token = runBlocking {
                tokenRepository.getToken()
            }
            Log.d("CODEXTOKENINTERCEPTOR", token.toString())

            val requestBuilder = chain.request().newBuilder()
            if (!token?.accessToken.isNullOrEmpty()) {
                requestBuilder.addHeader("Authorization", "Bearer ${token?.accessToken}")
            }
            chain.proceed(requestBuilder.build())
        }
    }
}

fun Context.saveAuthCode(code: String) {
    getSharedPreferences("auth", Context.MODE_PRIVATE)
        .edit()
        .putString("auth_code", code)
        .apply()
}

fun Context.getAuthCode(): String? {
    return getSharedPreferences("auth", Context.MODE_PRIVATE)
        .getString("auth_code", null)
}
