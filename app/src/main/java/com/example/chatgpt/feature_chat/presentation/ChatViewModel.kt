package com.example.chatgpt.feature_chat.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatgpt.MessageBody
import com.example.chatgpt.core.utils.ResourceState
import com.example.chatgpt.feature_chat.domain.use_cases.ChatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val chatting: ChatUseCase) : ViewModel() {
    private val _chatCompletion = mutableStateOf("")
    val chatCompletion: State<String> by lazy {
        _chatCompletion
    }

    fun chatCompletion(message: MessageBody) {
        viewModelScope.launch {
            chatting(message).collectLatest { chatResponse ->
                when (chatResponse) {
                    is ResourceState.Loading -> {

                    }
                    is ResourceState.Success -> {
                        chatResponse.data?.let {
                            _chatCompletion.value = it.choices[0].message.content
                        }
                    }
                    is ResourceState.Error -> {

                    }
                }
            }
        }
    }
}