package com.example.chatgpt.core.repositories

import com.example.chatgpt.ChatResponse
import com.example.chatgpt.MessageBody
import com.example.chatgpt.core.utils.ResourceState
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun getChatCompletion(message:MessageBody):Flow<ResourceState<ChatResponse>>
}