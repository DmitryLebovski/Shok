package com.example.shok

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.auth.AuthScreen
import com.example.notifications.NotificationScreen
import com.example.user.UserScreen
import com.example.shok.Routes.AUTH_SCREEN
import com.example.shok.Routes.NOTIFICATION_SCREEN
import com.example.shok.Routes.USER_SCREEN

object Routes {
    const val AUTH_SCREEN = "AuthScreen"
    const val USER_SCREEN = "UserScreen"
    const val NOTIFICATION_SCREEN = "NotificationScreen"
}

@Composable
fun AppNavigation(
    code: String?
) {
    val navController = rememberNavController()
    val app = LocalContext.current.applicationContext as ShokApp

    val authComponent = remember { app.appComponent.authSubcomponent().create() }
    var startDestination by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        val token = authComponent.authRepository().getToken()
        startDestination = if (!token?.access_token.isNullOrEmpty()) USER_SCREEN else AUTH_SCREEN
        Log.d("CODEXSTARTDEST", startDestination.toString())
    }

    if (startDestination == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    NavHost(
        navController = navController,
        startDestination = startDestination!!,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(AUTH_SCREEN) {
            AuthScreen(
                navigate = {
                    navController.navigate(USER_SCREEN) {
                        popUpTo(AUTH_SCREEN) { inclusive = true }
                    }
                },
                provider = authComponent,
                authCode = code
            )
        }

        composable(USER_SCREEN) {
            val userComponent = remember { app.appComponent.userSubcomponent().create() }
            UserScreen(
                navigateToNotifications = {
                    navController.navigate(NOTIFICATION_SCREEN)
                },
                navigateIfTokenExpired = {
                    navController.navigate(AUTH_SCREEN) {
                        popUpTo(USER_SCREEN) { inclusive = true }
                    }
                },
                provider = userComponent
            )
        }

        composable(NOTIFICATION_SCREEN) {
            val notificationComponent = remember { app.appComponent.notificationSubComponent().create() }
            NotificationScreen(
                navigateBack = {
                    navController.popBackStack()
                },
                navigateIfTokenExpired = {
                    navController.navigate(AUTH_SCREEN) {
                        popUpTo(NOTIFICATION_SCREEN) { inclusive = true }
                    }
                },
                provider = notificationComponent
            )
        }
    }
}