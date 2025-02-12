package com.example.features.notification_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.domain.UiState
import com.example.features.R
import com.example.features.user_screen.ErrorScreen
import com.example.features.user_screen.LoadingScreen

@Composable
fun NotificationScreen (
    navigate: () -> Unit,
    component: ProviderNotificationViewModel
) {
    val viewModel = remember { component.notificationViewModel() }
    val userNotifications by viewModel.userNotifications.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    when(uiState) {
        is UiState.Loading -> LoadingScreen()
        is UiState.Error -> ErrorScreen()
        is UiState.Success -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = navigate,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                ) { Text(stringResource(R.string.back)) }

                Text("Count ${userNotifications?.count.toString()}", modifier = Modifier.padding(vertical = 8.dp))

                Text("Page ${userNotifications?.page.toString()}", modifier = Modifier.padding(vertical = 8.dp))

                LazyColumn(
                    modifier = Modifier.padding(top = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    userNotifications?.let {
                        items(it.data) { data ->
                            NotificationCard(
                                date = data.date ?: "unknown",
                                unread = data.unread ?: false,
                                projectName = data.projectName ?: "unknown",
                                requestSubject = data.requestSubject ?: "unknown",
                                content = data.content ?: "unknown",
                                type = data.type ?: "unknown",
                                initiator = data.initiator ?: "unknown"
                            )
                        }
                    }
                }
            }
        }
    }
}