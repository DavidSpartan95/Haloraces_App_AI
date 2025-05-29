package com.davidspartan.haloracesai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davidspartan.haloracesai.ui.theme.Black13
import com.davidspartan.haloracesai.ui.theme.HaloracesAITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HaloracesAITheme {
                Box(
                    modifier = Modifier.fillMaxSize().background(Black13),
                    contentAlignment = Alignment.Center
                ){
                    GPTChatUI()
                }
            }
        }
    }
}

@Composable
fun GPTChatUI() {
    val viewModel = MsgViewModel()

    var userInput by remember { mutableStateOf("") }
    var gptResponse by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = userInput,
            onValueChange = { userInput = it },
            label = { "Ask GPT-4" }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            viewModel.callGPT(userInput) { response -> gptResponse = response }
        }) {
            Text("Send")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Response: $gptResponse")
    }
}
