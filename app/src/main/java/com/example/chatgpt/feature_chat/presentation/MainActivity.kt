package com.example.chatgpt.feature_chat.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.chatgpt.MessageBody
import com.example.chatgpt.MessageInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: ChatViewModel = hiltViewModel()
            val state = viewModel.chatCompletion.value

            var messages = remember { mutableStateListOf<Message>() }
            var text by remember { mutableStateOf("") }

            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn(Modifier.weight(1f)) {
                    items(messages) { message ->
                        ChatItem(message)
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text(text = "Type your message") }
                    )
                    Button(
                        onClick = {
                            viewModel.chatCompletion(
                                MessageBody(
                                    "gpt-3.5-turbo", arrayListOf(MessageInfo("user", text))
                                )
                            )
                            messages.add(Message("bot",state))
                            Log.d("taget",state)
                        },
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                    ) {
                        Text(text = "Send")
                    }
                }
            }
        }

    }
}
@Preview
@Composable
fun Preview(){
    val viewModel: ChatViewModel = hiltViewModel()
    val state = viewModel.chatCompletion.value

    var messages = remember { mutableStateListOf<Message>() }
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(Modifier.width(300.dp)) {
            items(messages) { message ->
                ChatItem(message)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text(text = "Type your message") }
            )
            Button(
                onClick = {
                    viewModel.chatCompletion(
                        MessageBody(
                            "gpt-3.5-turbo", arrayListOf(MessageInfo("user", text))
                        )
                    )
                    messages.add(Message("bot",state))
                },
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            ) {
                Text(text = "Send")
            }
        }
    }
}
@Composable
fun ChatItem(message: Message) {
    Row(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "${message.sender}: ${message.content}",
            modifier = Modifier.background(Color.LightGray),
            style = TextStyle(color = Color.Black)
        )
    }
}

data class Message(val sender: String, val content: String)