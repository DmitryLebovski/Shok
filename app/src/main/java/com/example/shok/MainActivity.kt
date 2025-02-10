package com.example.shok

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.features.auth_screen.AuthViewModel
import com.example.shok.ui.theme.ShokTheme

class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShokTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AppNavigation(
                        authViewModel
                    )
                }
            }
        }
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        val uri = intent?.data
        Log.d("CODECODE", uri.toString())
        if (uri?.scheme == "bellerage" && uri.host == "mobilelogin") {
            uri.getQueryParameter("code")?.let { code ->
                authViewModel.setAuthCode(code)
                Log.d("CODECODE", code)
            }
        }
    }
}