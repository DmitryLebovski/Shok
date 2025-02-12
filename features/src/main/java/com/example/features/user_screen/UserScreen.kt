package com.example.features.user_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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

@Composable
fun UserScreen(
    navigate: () -> Unit,
    component: ProviderUserViewModel
) {
    val viewModel = remember { component.userViewModel() }
    val user by viewModel.userInfo.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    when(uiState) {
        is UiState.Success -> {
            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { navigate() },
                    modifier = Modifier.width(160.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                )
                { Text(stringResource(R.string.notifications)) }

                Spacer(modifier = Modifier.padding(24.dp))

                Text(user.toString())
            }
        }

        is UiState.Loading -> { LoadingScreen() }
        is UiState.Error -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("failure")
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}