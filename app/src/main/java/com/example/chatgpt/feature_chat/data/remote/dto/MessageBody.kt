package com.example.chatgpt

data class MessageBody(
    val model:String,
    val messages:ArrayList<MessageInfo>
)

data class MessageInfo(
    val role:String,
    val content:String
)