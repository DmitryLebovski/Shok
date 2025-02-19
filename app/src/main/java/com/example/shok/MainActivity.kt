package com.example.shok

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.shok.ui.theme.ShokTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            ShokTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AppNavigation(handleIntent(intent))
                }
            }
        }
    }

    private fun handleIntent(intent: Intent?): String? {
        val uri = intent?.data
        Log.d("CODEXURI", uri.toString())
        return if (uri?.scheme == "bellerage" && uri.host == "mobilelogin") {
            uri.getQueryParameter("code")
        } else null
    }
}