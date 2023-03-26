package com.example.chatgpt

data class ChatResponse(
    val id:String,
    val `object`: String,
    val created:String,
    val choices:ArrayList<ChoicesResponse>,
    val usage:UsageResponse
)

class UsageResponse (
    val prompt_token:Int,
    val completion_tokens:Int,
    val total_tokens:Int
)

data class ChoicesResponse(
    val index:String,
    val message:MessageResponse,
    val finish_reason:String
)
data class MessageResponse(
    val role:String,
    val content:String
)