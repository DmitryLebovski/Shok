package com.example.shok

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
fun AppNavigation() {
    val navController = rememberNavController()
    val app = LocalContext.current.applicationContext as ShokApp

    NavHost(
        navController = navController,
        startDestination = AUTH_SCREEN,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(AUTH_SCREEN) {
            val authComponent = remember { app.appComponent.authSubcomponent().create() }
            AuthScreen(
                navigate = {
                    navController.navigate(USER_SCREEN)
                },
                component = authComponent
            )
        }

        composable(USER_SCREEN) {
            val userComponent = remember { app.appComponent.userSubcomponent().create() }
            UserScreen(
                navigate = {
                    navController.navigate(NOTIFICATION_SCREEN)
                },
                component = userComponent
            )
        }

        composable(NOTIFICATION_SCREEN) {
            val notificationComponent = remember { app.appComponent.notificationSubComponent().create() }
            NotificationScreen(
                navigate = {
                    navController.popBackStack()
                },
                component = notificationComponent
            )
        }
    }
}