package com.example.chatgpt.core.utils

sealed class ResourceState<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null, message: String? = null) : ResourceState<T>(data, message)
    class Success<T>(data: T, message: String? = null) : ResourceState<T>(data, message)
    class Error<T>(data: T? = null, message: String) : ResourceState<T>(data, message)
}