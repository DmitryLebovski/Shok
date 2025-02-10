package com.example.shok

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.features.AuthScreen
import com.example.features.NotificationScreen
import com.example.features.UserScreen
import com.example.shok.Routes.AUTH_SCREEN
import com.example.shok.Routes.NOTIFICATION_SCREEN
import com.example.shok.Routes.USER_SCREEN

object Routes {
    const val AUTH_SCREEN = "AuthScreen"
    const val USER_SCREEN = "UserScreen"
    const val NOTIFICATION_SCREEN = "NotificationScreen"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AUTH_SCREEN,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(AUTH_SCREEN) {
            AuthScreen(
                navigate = {
                    navController.navigate(USER_SCREEN)
                }
            )
        }

        composable(USER_SCREEN) {
            UserScreen(
                navigate = {
                    navController.navigate(NOTIFICATION_SCREEN)
                },
                accessToken = "someth"
            )
        }

        composable(NOTIFICATION_SCREEN) {
            NotificationScreen(
                navigate = {
                    navController.popBackStack()
                },
                accessToken = "someth"
            )
        }
    }
}