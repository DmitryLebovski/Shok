package com.example.notifications

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun NotificationCard(
    date: String,
    unread: Boolean,
    projectName: String,
    requestSubject: String,
    content: String,
    type: String,
    initiator: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(end = 36.dp)
        ) {
            Text(
                text = stringResource(R.string.date, date),
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = stringResource(R.string.unread, unread),
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = stringResource(R.string.project_name, projectName),
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = stringResource(R.string.request_subject, requestSubject),
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = stringResource(R.string.content, content),
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = stringResource(R.string.type, type),
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = stringResource(R.string.initiator, initiator),
                modifier = Modifier.padding(top = 8.dp)
            )

        }
    }
}