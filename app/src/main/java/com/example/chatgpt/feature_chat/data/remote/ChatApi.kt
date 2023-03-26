package com.example.chatgpt.feature_chat.data.remote

import com.example.chatgpt.ChatResponse
import com.example.chatgpt.MessageBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ChatApi {
    @POST("chat/completions")
    @Headers("Authorization: Bearer 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")
    suspend fun chatCompletion(@Body message: MessageBody):Response<ChatResponse>
}


