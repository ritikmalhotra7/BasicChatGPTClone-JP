package com.example.chatgpt.feature_chat.data.remote.repositories

import com.example.chatgpt.ChatResponse
import com.example.chatgpt.MessageBody
import com.example.chatgpt.core.repositories.ChatRepository
import com.example.chatgpt.core.utils.ResourceState
import com.example.chatgpt.feature_chat.data.remote.ChatApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ChatRepositoryImpl(private val api:ChatApi) :ChatRepository{
    override fun getChatCompletion(message: MessageBody): Flow<ResourceState<ChatResponse>> = flow {
        emit(ResourceState.Loading())
        val response = api.chatCompletion(message = message)
        if(response.errorBody() != null){
            emit(ResourceState.Error(message = response.message()))
            return@flow
        }
        emit(ResourceState.Success(data = response.body()!!))
    }
}