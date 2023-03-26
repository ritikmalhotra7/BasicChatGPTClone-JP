package com.example.chatgpt.feature_chat.domain.use_cases

import com.example.chatgpt.ChatResponse
import com.example.chatgpt.MessageBody
import com.example.chatgpt.core.repositories.ChatRepository
import com.example.chatgpt.core.utils.ResourceState
import kotlinx.coroutines.flow.Flow

class ChatUseCase(private val repository: ChatRepository) {
    operator fun invoke(message:MessageBody): Flow<ResourceState<ChatResponse>> {
        return repository.getChatCompletion(message)
    }
}