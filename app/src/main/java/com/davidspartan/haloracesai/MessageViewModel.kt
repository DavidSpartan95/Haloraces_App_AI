package com.davidspartan.haloracesai

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidspartan.haloracesai.api.ChatRequest
import com.davidspartan.haloracesai.api.Message
import com.davidspartan.haloracesai.api.openAIService
import kotlinx.coroutines.launch

class MsgViewModel(): ViewModel() {

    fun callGPT(message: String, onResult: (String) -> Unit) {
        viewModelScope.launch {
            val request = ChatRequest(
                messages = listOf(
                    Message("system", "You are a helpful assistant for Haloraces.com. You answer questions about race results, runners, and event history."),
                    Message("user", message)
                ),
            )
            val response = openAIService.getChatCompletion(request, "Bearer ${BuildConfig.API_KEY}")
            if (response.isSuccessful) {
                val reply = response.body()?.choices?.firstOrNull()?.message?.content
                onResult(reply ?: "No response")
            } else {
                onResult("Error: ${response.code()}")
            }
        }
    }
}
