package com.example.auth

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.auth.BuildConfig.*

@Composable
fun AuthScreen(
    navigate: (Boolean)-> Unit,
    provider: ProviderAuthUtils,
    authCode: String?,
) {
    val context = LocalContext.current
    val viewModel = viewModel {
        AuthScreenViewModel(
            provider.tokenUseCase()
        )
    }

    val token by viewModel.token.collectAsState()
    Log.d("CODEXCODE", authCode.toString())


    LaunchedEffect(token) {
        authCode?.let {
            if (viewModel.loadToken(it) && !token?.accessToken.isNullOrEmpty()) {
                Log.d("CODEXCODEALREADY", "navigate")

                navigate(true)
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = { context.launchCustomTabs(url = AUTH_URL) },
            modifier = Modifier.width(160.dp).height(40.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
        )
        {
            Text(
                stringResource(R.string.sign_in),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
            )
        }
    }
}

internal fun Context.launchCustomTabs(url: String) {
    CustomTabsIntent.Builder().build()
        .launchUrl(this, Uri.parse(url))
}

