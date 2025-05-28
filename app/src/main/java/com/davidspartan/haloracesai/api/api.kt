package com.davidspartan.haloracesai.api

import com.davidspartan.haloracesai.BuildConfig
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OpenAIService {
    @POST("v1/chat/completions")
    suspend fun getChatCompletion(
        @Body request: ChatRequest,
        @Header("Authorization") auth: String = BuildConfig.API_KEY
    ): Response<ChatResponse>
}

data class ChatRequest(
    val model: String = "gpt-4.1-nano",
    val messages: List<Message>,
    val max_tokens: Int = 100,
    val temperature: Double = 0.7
)

data class Message(
    val role: String,
    val content: String
)

data class ChatResponse(
    val choices: List<Choice>
)

data class Choice(
    val message: Message
)

val retrofit = Retrofit.Builder()
    .baseUrl("https://api.openai.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val openAIService = retrofit.create(OpenAIService::class.java)




