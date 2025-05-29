package com.davidspartan.haloracesai.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.davidspartan.haloracesai.MsgViewModel
import com.davidspartan.haloracesai.data.ChatMessage
import com.davidspartan.haloracesai.ui.theme.Black13

@Composable
fun GPTChatUI() {
    val viewModel: MsgViewModel = viewModel()

    var userInput by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf<ChatMessage>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            reverseLayout = true
        ) {
            items(messages.reversed()) { message ->
                ChatBubble(message = message.text, isUser = message.isUser)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                modifier = Modifier.weight(1f),
                value = userInput,
                onValueChange = { userInput = it },
                placeholder = { Text("Ask chat bot") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    val input = userInput.trim()
                    if (input.isNotEmpty()) {
                        messages.add(ChatMessage(input, isUser = true))
                        viewModel.callGPT(input) { response ->
                            messages.add(ChatMessage(response, isUser = false))
                        }
                        userInput = ""
                    }
                }
            ) {
                Text("Send")
            }
        }
    }
}



@Composable
fun ChatBubble(message: String, isUser: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = if (isUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(12.dp)
        ) {
            Text(
                text = message,
                color = Color.White
            )
        }
    }
}
