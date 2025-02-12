package com.example.features.notification_screen

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
import androidx.compose.ui.tooling.preview.Preview
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
                text = "Date: $date",
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "Unread: ${unread.toString()}",
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "Project Name: $projectName",
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "Request Subject $requestSubject",
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "Content $content",
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "Type $type",
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "Initiator $initiator",
                modifier = Modifier.padding(top = 8.dp)
            )

        }
    }
}

@Composable
@Preview
fun PreviewNotifCard(){
    NotificationCard(
        date = "TODO()",
        unread = false,
        projectName = "TODO()",
        requestSubject = "TODO()",
        content = "TODO()",
        type = "TODO()",
        initiator = "TODO()"
    )
}