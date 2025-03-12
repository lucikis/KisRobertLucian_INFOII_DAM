package com.example.chatapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chatapp.ui.theme.ChatAppTheme

class UserBActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val receivedMessage = intent?.getStringExtra("messageFromA") ?: ""

        enableEdgeToEdge()
        setContent {
            ChatAppTheme {
                UserBScreen(receivedMessage)
            }
        }
    }
}

@Composable
fun UserBScreen(receivedMessage: String) {
    val context = LocalContext.current
    var message by remember { mutableStateOf("") }
    var chatMessages by remember { mutableStateOf(listOf<String>()) }

    if (receivedMessage.isNotEmpty() && !chatMessages.contains("User A: $receivedMessage")) {
        chatMessages = chatMessages + "User A: $receivedMessage"
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(chatMessages) { msg ->
                Text(msg)
            }
        }

        TextField(
            value = message,
            onValueChange = { message = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Type a message...") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            if (message.isNotEmpty()) {
                chatMessages = chatMessages + "User B: $message"
                val intent = Intent(context, UserAActivity::class.java).apply {
                    putExtra("messageFromB", message)
                }
                context.startActivity(intent)
            }
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 40.dp)) {
            Text("Send to User A")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserBActivityPreview() {
    ChatAppTheme {
        UserBScreen("")
    }
}