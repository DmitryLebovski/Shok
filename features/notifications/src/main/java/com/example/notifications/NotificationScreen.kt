package com.example.notifications

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NotificationScreen (
    navigateBack: () -> Unit,
    provider: ProviderNotificationUseCase,
    token: String
) {
    val viewModel = viewModel { NotificationScreenViewModel(provider.notificationUseCase(), token) }
    val uiState by viewModel.uiState.collectAsState()

    when(uiState) {
        is NotificationsUiState.Loading -> LoadingScreen()
        is NotificationsUiState.Error -> {
            val error = (uiState as NotificationsUiState.Error).error
            Log.d("CODEXERROR", error.toString())

            ErrorScreen(error.message.toString())
        }
        is NotificationsUiState.Success -> {
            val notifications = (uiState as NotificationsUiState.Success).notificationResponse
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = navigateBack,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                    modifier = Modifier
                        .width(160.dp)
                        .height(44.dp)
                        .padding(top = 8.dp),
                ) {
                    Text(
                        stringResource(R.string.back),
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.Center)
                    )
                }

                Text(stringResource(R.string.count, notifications.count.toString()), modifier = Modifier.padding(vertical = 8.dp))

                Text(stringResource(R.string.page, notifications.page.toString()), modifier = Modifier.padding(vertical = 8.dp))

                LazyColumn(
                    modifier = Modifier.padding(top = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(notifications.data) { data ->
                        NotificationCard(
                            date = data.date,
                            unread = data.unread,
                            projectName = data.projectName,
                            requestSubject = data.requestSubject,
                            content = data.content,
                            type = data.type,
                            initiator = data.initiator
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun LoadingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorScreen(mes: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.failure, mes))
    }
}