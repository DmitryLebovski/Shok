package com.example.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
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
                    modifier = Modifier.width(160.dp).height(50.dp).padding(top = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                )
                {
                    Text(
                        stringResource(R.string.notifications),
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.Center)
                    )
                }

                Spacer(modifier = Modifier.padding(24.dp))

                Text(user.toString())
            }
        }

        is UiState.Loading -> LoadingScreen()
        is UiState.Error -> ErrorScreen()
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

@Composable
fun ErrorScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("failure")
    }
}